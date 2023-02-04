; --- lab4spel.asm

	.equ	VMEM_SZ     = 5		; #rows on display
	.equ	AD_CHAN_X   = 3		; ADC0=PA0, PORTA bit 0 X-led
	.equ	AD_CHAN_Y   = 4		; ADC1=PA1, PORTA bit 1 Y-led
	.equ	GAME_SPEED  = 70	; inter-run delay (millisecs)
	.equ	PRESCALE    = 7		; AD-prescaler value
	.equ	BEEP_PITCH  = 20	; Victory beep pitch
	.equ	BEEP_LENGTH = 100	; Victory beep length
	.equ    MS        =  20000; WAIT
	; ---------------------------------------
	; --- Memory layout in SRAM
	.dseg
	.org	SRAM_START
POSX:	.byte	1	; Own position
POSY:	.byte 	1
TPOSX:	.byte	1	; Target position
TPOSY:	.byte	1
LINE:	.byte	1	; Current line	
VMEM:	.byte	VMEM_SZ ; Video MEMory
SEED:	.byte	1	; Seed for Random

	; ---------------------------------------
	; --- Macros for inc/dec-rementing
	; --- a byte in SRAM
	.macro INCSRAM	; inc byte in SRAM
		lds	r16,@0
		inc	r16
		sts	@0,r16
	.endmacro

	.macro DECSRAM	; dec byte in SRAM
		lds	r16,@0
		dec	r16
		sts	@0,r16
	.endmacro

	; ---------------------------------------
	; --- Code
	.cseg
	.org 	$0
	jmp	START
	.org	INT0addr
	jmp	AVBROTT_0


START:
	ldi r16,HIGH(RAMEND); sätt stackpekaren
	out SPH,r16
	ldi r16, LOW(RAMEND)
	out SPL,r16
			
	call	HW_INIT	
	call	WARM


RUN:
	call	JOYSTICK
	call	ERASE_VMEM
	call	UPDATE
	call	 WAIT   ; våran delay som går igenom 70 millisekunder

	lds      r20, POSX ; Laddar in våra positioner samt datorns positioner
	lds      r21, POSY
	lds      r22,TPOSX
	lds      r23,TPOSY

		cp r20,r22  ;*** 	Vänta en stund så inte spelet går för fort 	** 	Avgör om träff				 	*
		brne NO_HIT
		cp r21,r23 ; Jämför våran position i både x-led och y-led

	brne	NO_HIT	
	ldi		r16,BEEP_LENGTH
	call	BEEP
	call	WARM


HW_INIT:
	push r16

	ldi r16,(1<<ISC01)|(1<<ISC00) ;*** 	flanktriggat avbrott på INT0 (PD2).			*
	out MCUCR,r16

	ldi r16,(1<<INT0)
	out GICR,r16
	
	ldi r16, $FF ;*** 	Konfigurera hårdvara och MUX-avbrott enligt *
	out DDRB, r16 ;*** 	ditt elektriska schema. Konfigurera 		*

	ldi r16, $07
	out DDRA, r16

	ldi r16, $01
	out DDRD, r16

	ldi r16,(1<<ADEN) ; Sätter en etta på AD enable "7"
	ori r16,PRESCALE; Takten på omvandlingen
	out ADCSRA,r16 

	pop r16
	sei			; display on
	ret


AVBROTT_0:
	push r16
	in r16,SREG 
	call MUX
	out SREG,r16
	pop r16
	reti


WAIT:
	push r25
	push r24
	ldi		r25,HIGH(MS)
	ldi		r24,LOW(MS)
	

WAIT_LOOP: 
	sbiw	r24,1
	brne	WAIT_LOOP
	pop		r24
	pop		r25
	ret
	


NO_HIT:
	jmp	RUN

	; ---------------------------------------
	; --- Multiplex display

MUX:
	//*** 	skriv rutin som handhar multiplexningen och *
	//*** 	utskriften till diodmatrisen. Öka SEED.		*
 
	push 	r16
	push     r17
	push	XL
	push	XH
	
	clr r17
	ldi		XH, HIGH(VMEM)
    ldi		XL, LOW(VMEM)
	lds 	r16, LINE
	add 	XL, r16
	clr		r16
	adc		XH, r16
	
	out		PORTB  ,r17
	lds		r16, LINE
	out		PORTA, r16
	ld		r16, X
	out		PORTB, r16

	INCSRAM SEED
	INCSRAM LINE
 
	cpi		r16, VMEM_SZ
	brne	OK_LINE
	clr		r16
	sts		LINE, r16
 
OK_LINE:
	pop		XH
	pop		XL
	pop     r17
	pop 	r16
	
	ret
 
	; ---------------------------------------
	; --- JOYSTICK Sense stick and update POSX, POSY
	; --- Uses r16

MUX_KLAR:
	st Z, r16
	pop ZH
	pop ZL
	pop XH
	pop XL

	ret
		
	; ---------------------------------------
	; --- JOYSTICK Sense stick and update POSX, POSY
	; --- Uses r16
MOVE_X:
	ldi		r16, AD_CHAN_X
	call	CONVERT
	cpi		r16,$03	;11/3 - rör sig åt vänster. 00/0 - höger.
	breq	INC_X
	cpi		r16, $00
	breq	DEC_X
	ret
DEC_X:
	INCSRAM		POSX ;öka på X
	ret
INC_X:
	DECSRAM		POSX ;minska X
	ret
	
MOVE_Y:
	ldi r16, AD_CHAN_Y 
	call CONVERT
	cpi r16, $03	;11 - uppåt. 00 - nedåt.
	breq INC_Y
	cpi r16,$00
	breq DEC_Y
	ret
INC_Y:
	INCSRAM POSY
	ret
DEC_Y:
	DECSRAM POSY
	ret

CONVERT:
	out		ADMUX,r16		 ;Väljer analog ingång, hanterar själv om det är X eller y. 
	sbi		ADCSRA,ADSC       ; starta en omvandling, ADCSRA aktiverar A/D omvandling. ADSC
CONVERT_WAIT:
	sbic	ADCSRA,ADSC       ; om nollställd är vi klara
	jmp		CONVERT_WAIT              ; annars testa busy-biten igen
	in		r16,ADCH          ; hög byte läses sedan in, tar bara in plats 9 & 8.
	ret	

JOYSTICK:
	rcall	MOVE_X
	rcall	MOVE_Y

JOY_LIM:
	call	LIMITS		; don't fall off world!
	ret

	; ---------------------------------------
	; --- LIMITS Limit POSX,POSY coordinates	
	; --- Uses r16,r17
LIMITS:
	lds	r16,POSX	; variable
	ldi	r17,7		; upper limit+1
	call	POS_LIM		; actual work
	sts	POSX,r16
	lds	r16,POSY	; variable
	ldi	r17,5		; upper limit+1
	call	POS_LIM		; actual work
	sts	POSY,r16
	ret

POS_LIM:
	ori	r16,0		; negative?
	brmi	POS_LESS	; POSX neg => add 1
	cp	r16,r17		; past edge
	brne	POS_OK
	subi	r16,2


POS_LESS:
	inc	r16
		
POS_OK:
	ret

	; ---------------------------------------
	; --- UPDATE VMEM
	; --- with POSX/Y, TPOSX/Y
	; --- Uses r16, r17
UPDATE:	
	clr	ZH 
	ldi	ZL,LOW(POSX)
	call 	SETPOS
	clr	ZH
	ldi	ZL,LOW(TPOSX)
	call	SETPOS
	ret

	; --- SETPOS Set bit pattern of r16 into *Z
	; --- Uses r16, r17
	; --- 1st call Z points to POSX at entry and POSY at exit
	; --- 2nd call Z points to TPOSX at entry and TPOSY at exit
SETPOS:
	ld	r17,Z+  	; r17=POSX
	call	SETBIT		; r16=bitpattern for VMEM+POSY
	ld	r17,Z		; r17=POSY Z to POSY
	ldi	ZL,LOW(VMEM)
	add	ZL,r17		; *(VMEM+T/POSY) ZL=VMEM+0..4
	ld	r17,Z		; current line in VMEM
	or	r17,r16		; OR on place
	st	Z,r17		; put back into VMEM
	ret
	
	; --- SETBIT Set bit r17 on r16
	; --- Uses r16, r17
SETBIT:
	ldi	r16,$01		; bit to shift
SETBIT_LOOP:
	dec 	r17			
	brmi 	SETBIT_END	; til done
	lsl 	r16		; shift
	jmp 	SETBIT_LOOP

SETBIT_END:
	ret

	; ---------------------------------------
	; --- Hardware init
	; --- Uses r16





	; ---------------------------------------
	; --- WARM start. Set up a new game
WARM:

;*** 	Sätt startposition (POSX,POSY)=(0,2)		*
;*** Satt startposition ( POSX , POSY )=(0 ,2) *
		push r16

		ldi r16, 0    ; Set starting xposition to 0
		sts POSX, r16   ; Write to SRAM posx
		ldi r16, 2    ; Set starting uposition to 2
		sts POSY, r16   ; Write to SRAM posy

		pop r16

		push r0
		push r0
		call RANDOM 

;*** RANDOM returns TPOSX, TPOSY on stack *
;*** Satt startposition ( TPOSX , TPOSY ) *
		
		pop r16
		sts TPOSX, r16  ; Store x value from stack
		pop r16
		sts TPOSY, r16	; Store y value from stack

		call ERASE_VMEM
    
		ret
	; ---------------------------------------
	; --- RANDOM generate TPOSX, TPOSY
	; --- in variables passed on stack.
	; --- Usage as:
	; ---	push r0 
	; ---	push r0 
	; ---	call RANDOM
	; ---	pop TPOSX 
	; ---	pop TPOSY
	; --- Uses r16
RANDOM:
        push ZH
		push ZL
		push r16 ;pushar in tre saker på stacken
		in r16, SPH
		mov ZH, r16
		in r16, SPL
		mov ZL, r16

		lds r16, SEED ;laddar med SEED, endast tre sista bitsen spelar roll, tar bort de 7 första
		andi   r16,$07

		;;kör på seed för att beräkna TPOSX

		cpi	r16,4 ;kollar position, om den är positiv branchas det till CONTROL_TPOSX
		brpl CONTROL_TPOSX
		subi r16,-4


CONTROL_TPOSX:
		cpi   r16, 7
		brne  LAGER_T ;kollar så att random siffran inte är lika med 7
		dec   r16


LAGER_T:
	std  Z+6,r16 ;lagrar det sjunde objektet på stacken på r16
	




	;här nere börjar random för Y.

	lds r16,SEED

CONTROL_Y:
		andi r16,$07
		cpi  r16,5       ; Is random lower than 5?
		brmi Y_KLAR     
		subi r16,3
Y_KLAR:
        std Z+7, r16        ; store POSY to stack
		pop r16
		pop ZL
		pop ZH

ret


	; ---------------------------------------
	; --- Erase Videomemory bytes
	; --- Clears VMEM..VMEM+4
	
ERASE_VMEM:
;*** 	Radera videominnet						*
push r16
push r17
push XL
push XH

	ldi XH, HIGH(VMEM)
	ldi XL, LOW(VMEM)

ldi r16,0
ldi r17,0
;laddar in alla positioner i VMEM med 0
E_LOOP:
st X+,r16
inc r17
cpi r17,VMEM_SZ ; när vi laddat alla med noll
brne E_LOOP

pop	XL
pop XH
pop r17
pop r16
	ret

	; ---------------------------------------
	; --- BEEP(r16) r16 half cycles of BEEP-PITCH

BEEP:
	ldi		r16,BEEP_LENGTH ; 100 long
BEEP_LOOP:
	sbi		PORTB, 7
	call	DELAY
	cbi		PORTB, 7
	call	DELAY
	dec		r16
	brne	BEEP_LOOP

	ret

DELAY:
	ldi		r25,HIGH(BEEP_PITCH)
	ldi		r24,LOW(BEEP_PITCH)
DELAY_LOOP: 
	sbiw	r24,1
	brne	DELAY_LOOP
	ret