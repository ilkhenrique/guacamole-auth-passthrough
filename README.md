Guacamole Auth Passthrough [![Build Status](https://travis-ci.org/edouardswiac/guacamole-auth-passthrough.svg?branch=master)](https://travis-ci.org/edouardswiac/guacamole-auth-passthrough)
=========
Deploy
```
cp guacamole-auth-passthrough-<version>.jar /etc/guacamole/extensions/
```
Build a config and get its corresponding token

```
POST yourguac.net/api/tokens
body: protocol=rdp&username=user1&password=p@ssw0rd&param1=var
```


```
{
  "authToken": "e1626c72adbd109a2431c9d707a96d8d12036d4cb890785421bcb427a7eefeb5",
  "username": "6f453490-72df-4feb-9718-ef8353f50d04",
  "dataSource": "ext.auth.passthrough",
  "availableDataSources": [
    "ext.auth.passthrough"
  ]
}
```

Display corresponding the remote session
```
GET yourguac.net/#/?token=e1626c72adbd109a2431c9d707a96d8d12036d4cb890785421bcb427a7eefeb5
```

Security note
------

This auth plugin is insecure by nature as it requires you to send credentials over the wire. HTTPS is strongly recommended!
