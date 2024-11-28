package com.example.planetze.ui.eco_balance;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;


import com.example.planetze.R;
import com.example.planetze.classes.EcoBalance.Project;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EcoBalanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EcoBalanceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EcoBalanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EcoBalanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EcoBalanceFragment newInstance(String param1, String param2) {
        EcoBalanceFragment fragment = new EcoBalanceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eco_balance, container, false);

        CardView projectCardView = view.findViewById(R.id.projectCard1);
        projectCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(new Project(
                        "Reforestation Project",
                        "Nairobi, Kenya",
                        "Sample",
                        "Reforestation in Kenya plays a critical role in combating desertification, restoring degraded lands, and mitigating the effects of climate change, particularly in areas like the Mau Forest and Mount Kenya. " +
                                "\n\nIt involves the planting of indigenous trees, such as acacia and cypress, which are well-adapted to local ecosystems, improving biodiversity and supporting wildlife habitats. " +
                                "\n\nThe Kenyan government, in collaboration with non-governmental organizations and local communities, has launched large-scale tree-planting campaigns, aiming to increase forest cover to at least 10% of the country's land area. " +
                                "\n\nCommunity-driven initiatives, like those spearheaded by the Green Belt Movement, empower locals, especially women, to lead tree-planting efforts while promoting environmental conservation and economic benefits. Additionally, reforestation efforts address water scarcity by rehabilitating watersheds, ensuring sustainable water supply for agriculture and urban centers, and enhancing resilience to droughts.",
                        "https://media.istockphoto.com/id/1156208490/photo/planting-a-young-tree.jpg?s=612x612&w=0&k=20&c=l876iwj5gE9L-7aqPSDow3UIGOdHXKTBx7m5flMfe8w=",
                        1,
                        2.5));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void showCustomDialog(Project project) {
        // Inflate the custom layout
        View customView = getLayoutInflater().inflate(R.layout.popup_template_checkout, null);

        // Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customView);

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();


        // Set title
        TextView titleText = customView.findViewById(R.id.textProjectTitle);
        TextView descriptionText = customView.findViewById(R.id.textProjectDescription);
        TextView locationText = customView.findViewById(R.id.textProjectLocation);
        TextView costText = customView.findViewById(R.id.textProjectCost);


        // Set all text related
        titleText.setText(project.getName());
        descriptionText.setText(project.getLongDescription());
        locationText.setText(project.getLocation());

        String costString = project.getPrice() + "/" + project.getCarbonCredits();
        costText.setText(costString);

        // Set image
        ImageView imageView = customView.findViewById(R.id.imageProject);
        Glide.with(this)
                .load(project.getImageLink())
                .placeholder(R.drawable.green_icon) // Optional: placeholder while loading
                // Optional: error placeholder
                .into(imageView);

        // Find and set listeners for buttons
        Button checkoutButton = customView.findViewById(R.id.buttonProjectCheckout);

        checkoutButton.setOnClickListener(v -> {
            // TODO: Implement google pay api
        });

        // Back Button
        Button backButton = customView.findViewById(R.id.buttonProjectBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}