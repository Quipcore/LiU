/*
 * delayTest.asm
 *
 *  Created: 2022-04-11 16:09:49
 *   Author: Felix
 */ 

; Rutinen DELAY är en vänteloop som sam-
; tidigt avger en skvallersignal på PB7.
; PB7 är hög när rutinen körs.
;
; Med angivet värde i r16 väntar rutinen
; ungef ?ar en millisekund @ 1 MHz.
;
; PORTB måste konfigureras separat.

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