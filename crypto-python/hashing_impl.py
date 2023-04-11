from Crypto.Hash import SHA3_256,MD5
h_obj = MD5.new()
h_obj.update(b'Some data')

print(h_obj.hexdigest())