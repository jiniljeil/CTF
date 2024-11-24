loose_keywords = [
    'union', 'sleep(', 'select', 'from', 'and', 
    'or', 'superadmin', 'if', 'having', '=', '>', 
    '<',' ', '*', '/', '\n', '\r', '\t', '\x0b', 
    '\x0c', '-', '+', '|', '&', '#'
]

select * from users where username='' and password=''   


- whitespace bypass
tab	%09	  \t 필터링으로 사용 불가
line feed	%0a	  \n 필터링으로 사용 불가
vertical tab	%0b	 \x0b 필터링으로 사용 불가 
form feed	%0c	  \x0c 필터링으로 사용 불가 
carriage return	%0d	  \r 필터링으로 사용 불가
/**/	 	  /, * 필터링으로 사용 불가


^, %, XOR, DIV, LIKE, RLIKE, REGEXP, IS, IN, NOT, BETWEEN, ISNULL
