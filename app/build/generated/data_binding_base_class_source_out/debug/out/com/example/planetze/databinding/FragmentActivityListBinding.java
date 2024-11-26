// Generated by view binder compiler. Do not edit!
package com.example.planetze.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.planetze.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentActivityListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button c1;

  @NonNull
  public final Button c2;

  @NonNull
  public final Button c3;

  @NonNull
  public final Button c4;

  @NonNull
  public final Button f1;

  @NonNull
  public final Button t1;

  @NonNull
  public final Button t2;

  @NonNull
  public final Button t3;

  @NonNull
  public final Button t4;

  private FragmentActivityListBinding(@NonNull ConstraintLayout rootView, @NonNull Button c1,
      @NonNull Button c2, @NonNull Button c3, @NonNull Button c4, @NonNull Button f1,
      @NonNull Button t1, @NonNull Button t2, @NonNull Button t3, @NonNull Button t4) {
    this.rootView = rootView;
    this.c1 = c1;
    this.c2 = c2;
    this.c3 = c3;
    this.c4 = c4;
    this.f1 = f1;
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentActivityListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentActivityListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_activity_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentActivityListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.c1;
      Button c1 = ViewBindings.findChildViewById(rootView, id);
      if (c1 == null) {
        break missingId;
      }

      id = R.id.c2;
      Button c2 = ViewBindings.findChildViewById(rootView, id);
      if (c2 == null) {
        break missingId;
      }

      id = R.id.c3;
      Button c3 = ViewBindings.findChildViewById(rootView, id);
      if (c3 == null) {
        break missingId;
      }

      id = R.id.c4;
      Button c4 = ViewBindings.findChildViewById(rootView, id);
      if (c4 == null) {
        break missingId;
      }

      id = R.id.f1;
      Button f1 = ViewBindings.findChildViewById(rootView, id);
      if (f1 == null) {
        break missingId;
      }

      id = R.id.t1;
      Button t1 = ViewBindings.findChildViewById(rootView, id);
      if (t1 == null) {
        break missingId;
      }

      id = R.id.t2;
      Button t2 = ViewBindings.findChildViewById(rootView, id);
      if (t2 == null) {
        break missingId;
      }

      id = R.id.t3;
      Button t3 = ViewBindings.findChildViewById(rootView, id);
      if (t3 == null) {
        break missingId;
      }

      id = R.id.t4;
      Button t4 = ViewBindings.findChildViewById(rootView, id);
      if (t4 == null) {
        break missingId;
      }

      return new FragmentActivityListBinding((ConstraintLayout) rootView, c1, c2, c3, c4, f1, t1,
          t2, t3, t4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}