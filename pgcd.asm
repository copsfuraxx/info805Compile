DATA SEGMENT
	v DD
DATA ENDS
CODE SEGMENT
	mov eax, 1
	push eax
	mov eax, 2
	pop ebx
	sub ebx, eax
	mov eax, ebx
	push eax
	mov eax, 3
	pop ebx
	sub ebx, eax
	mov eax, ebx
	mov v, eax
	out eax
CODE ENDS