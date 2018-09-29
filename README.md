# SearchView
A beautiful searchview with animations.

# Screens
<center>
<table>
  <tr>
    <th>SearchView Animation.</th>
  </tr>
 <tr>
    <td>
      <img src="/screen/search.gif" width="250" height="350" /
   </td>
 <tr>
</table>
	</center>	
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
	        implementation 'com.github.fevziomurtekin:SearchView:0.1.1'
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
public class MainActivity extends AppCompatActivity implements TextWatcher {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView      = new SearchView(getApplicationContext());
        searchView      . setSearchView_hint("Search");
        searchView      . setSearchView_textSize(14f);
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
| `searchhint` | Default text, "Search. " |
| `animationtime` |The size of the animation time is int (by default 250 ) |
| `searchtextcolor` | The color in int of the title text color (R.color.textcolor => #3F51B5) |
| `resulttextcolor` | The color in int of the title text color (R.color.textcolor => #3F51B5) |
| `searchsize` |The size in sp of the search text size (by default 14sp) |
| `resultsize`|The size in sp of the result text size (by default 14sp) |


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

