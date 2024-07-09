openssl s_client -connect i-am-confusion.2024.ductf.dev:30001 2>&1 < /dev/null | sed -n '/-----BEGIN/,/-----END/p' > certificatechain.pem
openssl x509 -pubkey -in certificatechain.pem -noout > pubkey.pem
openssl rsa -inform PEM -in pubkey.pem -pubin -RSAPublicKey_out -outform PEM > pubkey.rsa

node exploit.js