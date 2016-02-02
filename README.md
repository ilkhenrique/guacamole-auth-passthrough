Request:

POST ___console.gameanywhere.net/api/tokens___

- body params: protocol=rdp&username=user1&password=p@ssw0rd&param1=var


Response:

{
  "authToken": "e1626c72adbd109a2431c9d707a96d8d12036d4cb890785421bcb427a7eefeb5",
  "username": "6f453490-72df-4feb-9718-ef8353f50d04",
  "dataSource": "gameanywhere",
  "availableDataSources": [
    "gameanywhere"
  ]
}

Open a new window with the authToken
___console.gameanywhere.net/#/?token=e1626c72adbd109a2431c9d707a96d8d12036d4cb890785421bcb427a7eefeb5___ and this will display the remote desktop


// old params
{
  "protocol": "rdp",
  "username": "gamer",
  "password": "password",
  "security": "nla",
  "ignore-cert": "true",
  "hostname": "1.2.3.4",
  "width": "1024",
  "height": "768",
  "color-depth": "8",
  "server-layout": "us-utf8"
}