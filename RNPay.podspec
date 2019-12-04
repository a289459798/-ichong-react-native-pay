
Pod::Spec.new do |s|
  s.name         = "RNPay"
  s.version      = "1.0.0"
  s.summary      = "RNPay"
  s.description  = <<-DESC
                  RNPay
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "zhangzy@5ichong.com" }
  s.platform     = :ios, "9.0"
  s.source       = { :git => "https://gitee.com/petdoctor/react-native-pay.git", :tag => "master" }
  s.source_files  = "RNPay/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

