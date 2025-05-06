import os
import requests
from concurrent.futures import ThreadPoolExecutor

def download_image(i):
    number = f"{i:03}"
    url = f"https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/detail/{number}.png"
    path = os.path.join("./Info/Art", f"{number}.png")

    try:
        response = requests.get(url)
        if response.status_code == 200:
            with open(path, "wb") as f:
                f.write(response.content)
            print(f"Downloaded {number}.png")
        else:
            print(f"Not found: {number}.png (status code {response.status_code})")
    except Exception as e:
        print(f"Error downloading {number}.png: {e}")

with ThreadPoolExecutor(max_workers=32) as executor:
    executor.map(download_image, range(1, 1026))