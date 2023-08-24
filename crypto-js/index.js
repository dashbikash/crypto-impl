console.log("Welcome to Crypto")

const AES =require('./aes')
const hashing =require('./hashing')

var input="Hello World"
console.log("Plain Text: "+input)
enc =AES.AesCBCPkcs7Encrypt(input)
console.log(enc)
dec =AES.AesCBCPkcs7Decrypt(enc)
console.log(dec)

hashing.Hashing(input)


