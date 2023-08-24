package symmetric

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"errors"
	"io"

	"github.com/andreburgaud/crypt2go/padding"
)

func AesGcmEncrypt(plaintext []byte, key []byte) ([]byte, error) {
	c, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	gcm, err := cipher.NewGCM(c)
	if err != nil {
		return nil, err
	}

	nonce := make([]byte, gcm.NonceSize())
	if _, err = io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, err
	}

	return gcm.Seal(nonce, nonce, plaintext, nil), nil
}

func AesGcmDecrypt(ciphertext []byte, key []byte) ([]byte, error) {
	c, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	gcm, err := cipher.NewGCM(c)
	if err != nil {
		return nil, err
	}

	nonceSize := gcm.NonceSize()
	if len(ciphertext) < nonceSize {
		return nil, errors.New("ciphertext too short")
	}

	nonce, ciphertext := ciphertext[:nonceSize], ciphertext[nonceSize:]
	return gcm.Open(nil, nonce, ciphertext, nil)
}

// Sha256Key sha256 加密
func Sha256Key(key string) []byte {
	h := sha256.New()
	h.Write([]byte(key))
	newKey := h.Sum(nil)
	return newKey
}

// AesEncrypt 加密
func AesCbcPkcs7Encrypt(plainData, key []byte, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	paddedData, _ := padding.NewPkcs7Padding(aes.BlockSize).Pad(plainData)
	blockMode := cipher.NewCBCEncrypter(block, iv)
	crypted := make([]byte, len(paddedData))
	blockMode.CryptBlocks(crypted, paddedData)
	return crypted, nil
}

// AesDecrypt 解密
func AesCbcPkcs7Decrypt(ciperData, key []byte, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	blockMode := cipher.NewCBCDecrypter(block, iv)
	origData := make([]byte, len(ciperData))
	blockMode.CryptBlocks(origData, ciperData)
	origData, _ = padding.NewPkcs7Padding(aes.BlockSize).Unpad(origData)
	return origData, nil
}
