// Generated by view binder compiler. Do not edit!
package com.example.mainactivity.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ListView;
=======
import android.widget.ImageButton;
>>>>>>> 유아이
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mainactivity.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMapBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backbtn;

  @NonNull
  public final Button button2;

  @NonNull
  public final ListView searchListView;

  @NonNull
  public final SearchView searchView;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final Toolbar toolbar3;

<<<<<<< HEAD
  private ActivityMapBinding(@NonNull ConstraintLayout rootView, @NonNull Button button2,
      @NonNull ListView searchListView, @NonNull SearchView searchView, @NonNull TextView textView8,
=======
  private ActivityMapBinding(@NonNull ConstraintLayout rootView, @NonNull ImageButton backbtn,
      @NonNull Button button2, @NonNull SearchView searchView, @NonNull TextView textView8,
>>>>>>> 유아이
      @NonNull Toolbar toolbar3) {
    this.rootView = rootView;
    this.backbtn = backbtn;
    this.button2 = button2;
    this.searchListView = searchListView;
    this.searchView = searchView;
    this.textView8 = textView8;
    this.toolbar3 = toolbar3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMapBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMapBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_map, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMapBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backbtn;
      ImageButton backbtn = ViewBindings.findChildViewById(rootView, id);
      if (backbtn == null) {
        break missingId;
      }

      id = R.id.button2;
      Button button2 = ViewBindings.findChildViewById(rootView, id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.searchListView;
      ListView searchListView = ViewBindings.findChildViewById(rootView, id);
      if (searchListView == null) {
        break missingId;
      }

      id = R.id.searchView;
      SearchView searchView = ViewBindings.findChildViewById(rootView, id);
      if (searchView == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.toolbar3;
      Toolbar toolbar3 = ViewBindings.findChildViewById(rootView, id);
      if (toolbar3 == null) {
        break missingId;
      }

<<<<<<< HEAD
      return new ActivityMapBinding((ConstraintLayout) rootView, button2, searchListView,
          searchView, textView8, toolbar3);
=======
      return new ActivityMapBinding((ConstraintLayout) rootView, backbtn, button2, searchView,
          textView8, toolbar3);
>>>>>>> 유아이
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
