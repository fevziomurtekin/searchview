# SearchView
A beautiful searchview with animations.

# Screens
<table>
  <tr>
    <th>SearchView Animation.</th>
  </tr>
  <tr>
    <td>
      <center><img src="/screen/pincode.gif" width="250" height="350" /></center>
    </td>
  </tr>
</table>
</br>

# Usage

```Gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  .....

  dependencies {
	        implementation 'com.github.fevziomurtekin:lockscreen:0.1.1'
	  }
	}
	```
  </br> Include layout.
  `
  ```Gradle
  `<com.fevziomurtekin.searchview.SearchView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/search"
        />
      ```  
        
  </br> Include in the activity 
  ```Gradle 
 public class MainActivity extends LookScreen {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView      = new SearchView(getApplicationContext());
        searchView      . setSearchView_hint("Search");
        searchView      . setResult_textSize(14f);
        searchView      . setSearchView_animationTime(250);
        searchView      . setSearchView_textColor(getResources().getColor(R.color.borderColor));
        searchView      . setResult_textSize(14f);
        searchView      . setResult_textColor(getResources().getColor(R.color.borderColor));
    }
}
  ```
  
  # Attributes

  | Attribute | Description |
| --- | --- |
| `title` | Default text, "Enter your password. " |
| `titleSize` | The size in sp of the title text size (by default 15sp) |
| `titleColor` | The color in int of the title text color (R.color.black) |
| `message` | The value in string of the message items (by default "Log in with your password or fingerprint reader.")  |
| `messageSize` |The size in sp of the message text size (by default 14sp) |
| `messageColor` | The color in int of the title text color (R.color.black) |
| `error` | The value in string of the error items (by default "You entered an incorrect password. Please try again.") |
| `errorSize` | The size in sp of the title text size (by default 14sp) |
| `errorColor` | The color in int of the title text color (R.color.red) |
| `intent` | It is for transferring to another class when successfully logged in |
| `pass` | The value in string of the password items (by default "1234") |


## License

    	MIT License

    	Copyright (c) 2018 Fevzi Ömür Tekin

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.

