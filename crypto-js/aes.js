var CryptoJS=require('crypto-js')

var key=CryptoJS.enc.Utf8.parse("0123456789abcdef0123456789abcdef")
var iv=CryptoJS.enc.Utf8.parse("0123456789abcdef")

function AesCBCPkcs7Encrypt(plainText){
    enc =CryptoJS.AES.encrypt(plainText,key,{
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7,
        iv:iv
    });
    
    return enc.toString()
}

function AesCBCPkcs7Decrypt(cipherText){
    cipherText=CryptoJS.enc.Base64.parse(cipherText)

    dec =CryptoJS.AES.decrypt(enc,key,{
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7,
        iv:iv
    })

    return CryptoJS.enc.Utf8.stringify(dec)
}



module.exports = { AesCBCPkcs7Decrypt,AesCBCPkcs7Encrypt }