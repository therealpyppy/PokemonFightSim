import requests
import math

with open('./Info/Pokemon.csv', 'r') as base:
	with open('./Pokemon2.csv', 'w') as newFile:
		for i, v in enumerate(base.readlines(), 1):
			url = f"https://pokeapi.co/api/v2/pokemon/{v.split(',')[1].strip("\"")}"
			response = requests.get(url)
			if response.status_code == 200:
				for move in response.json()["moves"][:max(1, min(len(response.json()["moves"]), 4))]:
					moveUrl = move["move"]["url"].strip("\"")
					moveResponse = requests.get(moveUrl).json()

					moveInfo = []
					moveInfo.append(moveResponse['id'])
					moveInfo.append(moveResponse['name'])
					moveInfo.append(moveResponse['accuracy'])
					moveInfo.append(moveResponse['power'])
					moveInfo.append(moveResponse['pp'])
					moveInfo.append(moveResponse['type']['name'])
					print(moveInfo)
				break