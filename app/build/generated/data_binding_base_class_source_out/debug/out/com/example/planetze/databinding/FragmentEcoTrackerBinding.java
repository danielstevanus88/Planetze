// Generated by view binder compiler. Do not edit!
package com.example.planetze.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.planetze.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentEcoTrackerBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final LinearLayout buttonPickDate;

  @NonNull
  public final EditText editTextDate;

  @NonNull
  public final ImageView imageButtonCalendar;

  @NonNull
  public final TextView textPickADate;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  private FragmentEcoTrackerBinding(@NonNull FrameLayout rootView,
      @NonNull LinearLayout buttonPickDate, @NonNull EditText editTextDate,
      @NonNull ImageView imageButtonCalendar, @NonNull TextView textPickADate,
      @NonNull TextView textView2, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.buttonPickDate = buttonPickDate;
    this.editTextDate = editTextDate;
    this.imageButtonCalendar = imageButtonCalendar;
    this.textPickADate = textPickADate;
    this.textView2 = textView2;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentEcoTrackerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentEcoTrackerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_eco_tracker, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentEcoTrackerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonPickDate;
      LinearLayout buttonPickDate = ViewBindings.findChildViewById(rootView, id);
      if (buttonPickDate == null) {
        break missingId;
      }

      id = R.id.editTextDate;
      EditText editTextDate = ViewBindings.findChildViewById(rootView, id);
      if (editTextDate == null) {
        break missingId;
      }

      id = R.id.imageButtonCalendar;
      ImageView imageButtonCalendar = ViewBindings.findChildViewById(rootView, id);
      if (imageButtonCalendar == null) {
        break missingId;
      }

      id = R.id.textPickADate;
      TextView textPickADate = ViewBindings.findChildViewById(rootView, id);
      if (textPickADate == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      return new FragmentEcoTrackerBinding((FrameLayout) rootView, buttonPickDate, editTextDate,
          imageButtonCalendar, textPickADate, textView2, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
