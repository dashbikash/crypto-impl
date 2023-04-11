import base64
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP

secret_key="MySecretKey"

def gen_private_key():    
    key=RSA.generate(2048)
    enc_key=key.export_key(passphrase=secret_key,pkcs=8,protection="scryptAndAES128-CBC")
    #print(enc_key.decode())
    return enc_key.decode()

def gen_public_key(private_key:str):
    key_byte=private_key.encode()
    key = RSA.import_key(key_byte, passphrase=secret_key)
    print(key.publickey().export_key())
    return key.publickey().export_key().decode()

def encrypt_text(public_key:str,plain_text:str):
    p_key=RSA.import_key(public_key.encode())
    encrypter=PKCS1_OAEP.new(p_key)
    enc_byte=encrypter.encrypt(plain_text.encode())
    return  enc_byte.hex()

def decrypt_text(private_key:str,enc_text:str):
    print(private_key)
    key=RSA.import_key(private_key.encode(),passphrase=secret_key)
    decryptor=PKCS1_OAEP.new(key)
    dec_byte=decryptor.decrypt(bytes.fromhex(enc_text))
    return  dec_byte.decode()

if __name__=='__main__':
    ip_text='Bikash P Dash'
    pvt_key=gen_private_key()
    pub_key=gen_public_key(pvt_key)
    enc_text=encrypt_text(pub_key,ip_text)
    print(enc_text)
    plain_text=decrypt_text(pvt_key,enc_text)
    print(plain_text)
    #print("Input: %s\nPvt Key: %s\Pub Key: %s"%(ip_text,pvt_key,pub_key))