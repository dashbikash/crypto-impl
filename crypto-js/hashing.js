var CryptoJS=require('crypto-js')

// Hashing
function Hashing(input) {
    console.log("SHA256:\n"+CryptoJS.SHA256(input).toString(CryptoJS.enc.Base64)+"\n")
    console.log("MD5:\n"+CryptoJS.MD5(input).toString(CryptoJS.enc.Base64)+"\n")
}


module.exports={Hashing}