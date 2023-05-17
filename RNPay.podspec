require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "RNPay"
  s.version      = package["version"]
  s.summary      = package['description']
  s.author       = package['author']
  s.homepage     = package['homepage']
  s.license      = package['license']
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "zhangzy@5ichong.com" }
  s.platform     = :ios, "9.0"
  s.source       = { :git => "https://gitee.com/petdoctor/react-native-pay.git", :tag => "master" }
  s.source_files  = "ios/**/*.{h,m}"
  s.requires_arc = true
  s.ios.vendored_libraries = 'ios/Assets/*.a'

  s.dependency "React"
  s.dependency "WechatOpenSDK", '1.8.7.1'
  s.dependency "AlipaySDK-iOS"

  s.subspec 'openssl' do |openssl|
    openssl.source_files = 'ios/openssl/**/*'
    openssl.public_header_files = 'ios/openssl/**/*.h'

  end

  s.subspec 'alipay' do |alipay|
    alipay.source_files = 'ios/alipay/**/*'
    alipay.public_header_files = 'ios/alipay/**/*.h'
  end
end

