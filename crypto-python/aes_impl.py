import base64
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad,unpad


key = b"MySecretAesKey00" # 16 byte for AES
iv=b"MySecretAesIV000"
def encrypt_text(plain_text:str):
    cipher = AES.new(key, AES.MODE_CBC,IV=iv)
    ciphertext = cipher.encrypt(pad(data_to_pad=plain_text.encode('utf-8'),block_size=AES.block_size))
    print(base64.b64encode(ciphertext).decode('utf-8'))
    return base64.b64encode(ciphertext).decode('utf-8')

def decrypt_text(cipher_text:str):
    cipher_text=base64.b64decode(cipher_text.encode('utf-8'))
    cipher = AES.new(key, AES.MODE_CBC,iv=iv)
    plain = unpad(cipher.decrypt(cipher_text), AES.block_size)
    print(plain.decode('utf-8'))
    return plain.decode('utf-8')

if __name__=='__main__':
    ip_text='Bikash P Dash'
    cp_text=encrypt_text(ip_text)
    plain_text=decrypt_text(cp_text)
    print("Input: %s\nEnc Text: %s\nDec Text: %s"%(ip_text,cp_text,plain_text))