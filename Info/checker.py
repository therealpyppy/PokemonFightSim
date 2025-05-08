import os

with open("./Info/Pokemon.csv", 'r') as pokeFile:
	passed = 0
	total = 1025
	for lineNum, lineContent in enumerate(pokeFile.readlines()):
		errs = []
		if len(lineContent.split(",")) != 13:
			if len(lineContent.split(",")) < 13:
				errs.append("Missing data")
			if len(lineContent.split(",")) < 13:
				errs.append("Unkown data found")

		elif not os.path.exists(f"./Info/Art/{lineContent.split(",")[0].strip("\"")}.png"):
			errs.append(f"Image not found for id {lineContent.split(",")[0].strip("\"")}")

		if len(errs) != 0:
			print(f"❌ {lineNum}:")
			for msg in errs:
				print(msg)
		else:
			print(f"✅ {lineNum}: All checks passed! ({passed}/{total})")
			passed+=1