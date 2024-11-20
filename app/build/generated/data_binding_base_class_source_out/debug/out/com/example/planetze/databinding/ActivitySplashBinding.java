// Generated by view binder compiler. Do not edit!
package com.example.planetze.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.planetze.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySplashBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView catchphrase;

  @NonNull
  public final TextView description;

  @NonNull
  public final ImageView earthGraphic;

  @NonNull
  public final ImageView logo;

  @NonNull
  public final RelativeLayout main;

  private ActivitySplashBinding(@NonNull RelativeLayout rootView, @NonNull TextView catchphrase,
      @NonNull TextView description, @NonNull ImageView earthGraphic, @NonNull ImageView logo,
      @NonNull RelativeLayout main) {
    this.rootView = rootView;
    this.catchphrase = catchphrase;
    this.description = description;
    this.earthGraphic = earthGraphic;
    this.logo = logo;
    this.main = main;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySplashBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_splash, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySplashBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.catchphrase;
      TextView catchphrase = ViewBindings.findChildViewById(rootView, id);
      if (catchphrase == null) {
        break missingId;
      }

      id = R.id.description;
      TextView description = ViewBindings.findChildViewById(rootView, id);
      if (description == null) {
        break missingId;
      }

      id = R.id.earth_graphic;
      ImageView earthGraphic = ViewBindings.findChildViewById(rootView, id);
      if (earthGraphic == null) {
        break missingId;
      }

      id = R.id.logo;
      ImageView logo = ViewBindings.findChildViewById(rootView, id);
      if (logo == null) {
        break missingId;
      }

      RelativeLayout main = (RelativeLayout) rootView;

      return new ActivitySplashBinding((RelativeLayout) rootView, catchphrase, description,
          earthGraphic, logo, main);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}