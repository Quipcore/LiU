/*
 * delayTest.asm
 *
 *  Created: 2022-04-11 16:09:49
 *   Author: Felix
 */ 

; Rutinen DELAY �r en v�nteloop som sam-
; tidigt avger en skvallersignal p� PB7.
; PB7 �r h�g n�r rutinen k�rs.
;
; Med angivet v�rde i r16 v�ntar rutinen
; ungef ?ar en millisekund @ 1 MHz.
;
; PORTB m�ste konfigureras separat.

START:
	call DELAY

END:
	jmp END


DELAY: ;Short delay
	ldi     r16,$FF 
delayYttreLoop:
	ldi     r17,$FF
delayInreLoop0:
	ldi		r18,$78
delayInreLoop1:
	dec		r18
	brne	delayInreLoop1
	dec     r17
	brne    delayInreLoop0
	dec     r16
	brne    delayYttreLoop
	ret

; @ 16MHz
;255,255,83	 -> 1.02419206ms
;255,255,100 -> 1,231ms
;0xFF,0xFF,0x78 -> 1.47530300ms
;255,255,150 -> 1.84106862ms
;255,255,175 -> 2.14587331ms
;0xFF,0xFF,0xF0 -> 2.93836550ms
;255,255,250 -> 3.06028738