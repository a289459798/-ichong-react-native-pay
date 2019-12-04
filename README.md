
# react-native-pay

## Getting started

`$ npm install react-native-pay --save`

### Mostly automatic installation

`$ react-native link react-native-pay`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-pay` and add `RNPay.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNPay.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.zzy.pay.RNPayPackage;` to the imports at the top of the file
  - Add `new RNPayPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-pay'
  	project(':react-native-pay').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-pay/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-pay')
  	```


## Usage
```javascript
import RNPay from 'react-native-pay';

// TODO: What to do with the module?
RNPay;
```
  