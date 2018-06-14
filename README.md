# springCloudDemo
#keytool 生成证书 ，用处是https安全证书 注意OU对应域名
 keytool -genkey -alias jdttkeystore -keyalg RSA -keystore C:/Users/yaopchen/Documents/jdttkeystore
 
 
 keytool -export -file C:/Users/yaopchen/Documents/jdttkeystore.crt -alias jdttkeystore -keystore C:/Users/yaopchen/Documents/jdttkeystore
 
 keytool -delete -alias jdttkeystore -keystore cacerts -storepass changeit 
 
 
 keytool -import -keystore cacerts -file C:/Users/yaopchen/Documents/jdttkeystore.crt -alias jdttkeystore  -storepass changeit
 
 
