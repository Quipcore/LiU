;
; TSEA77Labb1.asm
;
; Created: 2022-04-11 11:55:41
; Author : Felix
;

	ldi	r16,HIGH(RAMEND)
	out	SPH,r16
	ldi	r16,LOW(RAMEND)
	out	SPL,r16

INIT:
	//Clear input
	cbi	DDRA,0 ;Makes sure you cant output in A
	ldi	r16,$FF
	out	DDRB,r16 //Enables all B Ports for output

	ldi	r16,$0
	out		PORTB,r16 ;reset portB to 0

START:
	call	WAIT_FOR_KEY
	call	DELAY_SHORT

	ldi		r19,4
	clr		r16
LOOP: ;Migth result in num written backwards
	lsl		r16		;Make sure r16 is totally clear before first iteration
	in		r17,PINA
	or		r16,r17

	//call	DELAY_LONG ;comment during debug and testning
	dec		r19
	brne	loop

OUTPUT:
	out		PORTB,r16 ;Connect PORTB to 7seg display


END: ; Maybe add wait_for_key sub-routine to be albe to write multiple nums
	jmp END

WAIT_FOR_KEY:
	sbis	PINA,0
	jmp		WAIT_FOR_KEY
	ret

DELAY_LONG:
	call	DELAY_SHORT
	call	DELAY_SHORT
	ret

// r22 = $78 -> 1.475ms in delayTest
DELAY_SHORT: ;Short delay
	ldi     r20,$FF 
delayOuterLoop:
	ldi     r21,$FF
delayInnerLoop0:
	ldi		r22,$78
delayInnerLoop1:
	dec		r22
	brne	delayInnerLoop1
	dec     r21
	brne    delayInnerLoop0
	dec     r20
	brne    delayOuterLoop
	ret
