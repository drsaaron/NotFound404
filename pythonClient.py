#! /usr/bin/env python3

import requests
from requests.exceptions import HTTPError

baseUrl = 'http://localhost:8080/v1/person'

def getPerson(id):
    url = f"{baseUrl}/{id}"
    response = requests.get(url)
    
    if (response.ok):
        print("person " + str(id) + " = " + str(response.json()))
    elif (response.status_code == 404):
        print("person " + str(id) + " not found")
    else:
        response.raise_for_status()

getPerson(1)

try:
    getPerson(10)
except HTTPError:
    print("tolerating http error")

