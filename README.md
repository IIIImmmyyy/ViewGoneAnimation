# ViewGoneAnimation
this is a View Gone Animation for Android .


<br> How to use
===

 ### this lib have some bug  if you want to use it, please don't setVisibility GONE IN XML.  you want  you can setVisibility(GONE) in code, such as
 ```aidl
ViewAnimationHelper.getInstance().
                 attach(testBtn).setVisibility(View.GONE);

```

### Normal
 ```aidl
   ViewAnimationHelper.getInstance().
                        attach(nidaye).setVisibility(View.GONE);
```

```aidl
ViewAnimationHelper.getInstance().attach(testBtn).setVisibility(View.VISIBLE);
```

```html
 <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <RelativeLayout
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
            <Button
                android:id="@+id/test_btn"
                android:layout_width="match_parent"
                android:layout_height="100dp"
                android:text="test btn"
                android:visibility="visible"/>

            <TextView
                android:id="@+id/ni_daye"
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_alignParentLeft="true"
                android:layout_alignParentStart="true"
                android:layout_below="@+id/test_btn"
                android:gravity="center"
                android:text="this is text view"/>
        </RelativeLayout>
        <Button
            android:id="@+id/first_btn"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:text="First Button"/>

        <Button
            android:id="@+id/sec_btn"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:text="hide First Btn"/>

        <Button
            android:id="@+id/show_all_btn"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:text="show Btn"/>
    </LinearLayout>
```
``

##this lib is support any view . so long as it extends ViewGroup
 
#if you have suggest or want support more animation , you can commit in Issuse .
 

