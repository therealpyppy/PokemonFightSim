with open('./Info/Pokemon.csv', 'r') as poke:
	with open('./Pokemon2.csv', 'w') as poke2:
		temp = set()
		for i, v in enumerate(poke.readlines(), 1):
			print(v.split(',')[0])
			print(not v.split(',')[0] in temp)
			if not v.split(',')[0] in temp:
				poke2.write(v)
				temp.add(v.split(',')[0])