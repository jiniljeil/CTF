*Important Notes*
  
- The hosted challenge would have the ChatGPT WAF (AKA WAIFU) enabled. The ChatGPT WAF will not work locally unless you provide it an OPENAI API key to use.
- Only the `www-bot` container is exposed on the hosted instance. It is recommended to locally test your payloads first.
- Use **`127.0.0.1`** (not `localhost`) instead of container names (the ports the services are listening on are the same). 
