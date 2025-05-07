with open("./Info/Pokemon.csv", 'r') as pokeFile:
	errs = []
	passed = 0
	total = 1026
	for lineNum, lineContent in enumerate(pokeFile.readlines()):
		if len(lineContent.split(",")) != 13:
			if len(lineContent.split(",")) < 13:
				errs.append("Missing data")
			if len(lineContent.split(",")) < 13:
				errs.append("Unkown data found")

		if errs.__len__ != 0:
			print(f"❌ {lineNum}:")
			for msg in errs:
				print(msg)
		else:
			print(f"✅ {lineNum}: All checks passed! ({passed}/{total})")
			passed+=1