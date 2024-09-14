import random


names = '''james smith
michael smith
robert smith
david smith
james johnson
michael johnson
william smith
james williams
robert johnson
mary smith
james brown
john smith
david johnson
michael brown
maria garcia
michael williams
michael jones
james jones
maria rodriguez
robert brown
michael miller
robert jones
robert williams
william johnson
james davis
mary johnson
maria martinez
charles smith
david brown
robert miller
james miller
john williams
richard smith
david williams
david jones
michael davis
william brown
david miller
mary williams
jennifer smith
william jones
john johnson
john miller
daniel smith
thomas smith
linda smith
james wilson
robert davis
mary brown
mary jones
patricia smith
james moore
james taylor
william miller
john davis
charles johnson
william davis
john jones
richard johnson
james anderson
robert taylor
barbara smith
michael moore
james martin
michael wilson
james thomas
joseph smith
james white
mary miller
robert anderson
robert wilson
charles williams
jennifer johnson
michael anderson
john brown
michael martin
james thompson
mark smith
michael thomas
david anderson
linda johnson
elizabeth smith
mary davis
james jackson
michael taylor
charles brown
daniel garcia
james lee
michael thompson
daniel johnson
david wilson
thomas johnson
john anderson
robert moore
john wilson
richard brown
charles jones
mark johnson
robert lee'''.split("\n")



query  = "INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES (\"%s\", \"%s\", \"%s\", \"%s\");"

for name in names:
	emonth = str(random.randint(1,12)).rjust(2, "0")
	eday = str(random.randint(1,29)).rjust(2, "0")


	by = random.choice(["99","02","01","00","97"])
	bmonth = str(random.randint(1,12)).rjust(2, "0")
	bday = str(random.randint(1,29)).rjust(2, "0")

	enlist_date = emonth+"/"+eday
	birthday = by+bmonth+bday

	result_unit = random.choice(["Nonsan", "Paju", "Gyeongsangbuk-do"])
	print(query % (name, birthday, enlist_date, result_unit))