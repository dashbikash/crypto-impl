package main

import (
	"encoding/base64"
	"fmt"

	"github.com/dashbikash/crypto-impl/crypto-go/symmetric"
)

func main() {
	fmt.Println("Welcome Crypto")
	plainbyte := []byte("Hello World")
	key := []byte("0123456789abcdef0123456789abcdef")
	iv := []byte("0123456789abcdef")
	if encdata, err := symmetric.AesCbcPkcs7Encrypt(plainbyte, key, iv); err != nil {
		panic(err)
	} else {
		fmt.Println(base64.StdEncoding.EncodeToString(encdata))
		if plainData, err := symmetric.AesCbcPkcs7Decrypt(encdata, key, iv); err != nil {
			panic(err)
		} else {
			fmt.Println(string(plainData))
		}

	}

}
