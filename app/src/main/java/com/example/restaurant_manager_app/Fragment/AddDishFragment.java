package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.R;


public class AddDishFragment extends Fragment implements RunSql {

    View view;
    EditText edName, edDescribe, edVote, edPrice, edImage;
    Button btnAdd, btnChooseImg;
    ImageView img_item, imgBack;
    TextView btnPreview;
    MainActivity mMainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_add_dish, container, false);

        init();
        mapping();
        updateView();
        setClick();
        return view;
    }

    private void init() {
        mMainActivity = (MainActivity) getActivity();
    }

    private void mapping() {
        edName = view.findViewById(R.id.edName);
        edDescribe = view.findViewById(R.id.edDescribe);
        edVote = view.findViewById(R.id.edVote);
        edPrice = view.findViewById(R.id.edPrice);
        edImage = view.findViewById(R.id.edImage);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnChooseImg = view.findViewById(R.id.btn_chonanh);
        img_item = view.findViewById(R.id.imgPreView);
        btnPreview = view.findViewById(R.id.btnPreview);
        imgBack = view.findViewById(R.id.imgBack);
    }

    private void setClick() {
//        btnChooseImg.setOnClickListener(v -> {
//            requestPermissonChangImage();
//        });
        imgBack.setOnClickListener(v -> mMainActivity.replaceFragmentSetting());
        btnPreview.setOnClickListener(v -> Glide.with(requireContext()).load(edImage.getText().toString()).into(img_item));
        btnAdd.setOnClickListener(v -> addDish());
    }

    private void updateView() {
    }

    private boolean validate() {
        String name = edName.getText().toString();
        String describe = edDescribe.getText().toString();
        String vote = edVote.getText().toString();
        String price = edPrice.getText().toString();
        String image = edImage.getText().toString();
        if (name.equals("") || describe.equals("") || vote.equals("") || price.equals("") || image.equals("")) {
            String m = "Bạn phải nhập đầy đủ thông tin";
            noitifyR(m);
            return false;
        }
        return true;
    }

    private void addDish() {
        String name = edName.getText().toString();
        String describe = edDescribe.getText().toString();
        String vote = edVote.getText().toString();
        String price = edPrice.getText().toString();
        String image = edImage.getText().toString();
        String sql = "INSERT INTO `food` (`id`, `name`, `describes`, `vote`, `price`, `image`) VALUES (NULL, '" +
                name +
                "', '" +
                describe +
                "', '" +
                vote +
                "', '" +
                price +
                "', '" +
                image +
                "')";
        if (validate()) {
            new ApiRunSql(sql, this).execute();
        }
    }


    @Override
    public void start() {
        //Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    private void noitifyR(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("" + message);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void finish() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Hoàn thành");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }

//    private void requestPermissonChangImage() {
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                openImagePicker();
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        TedPermission.create()
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .check();
//    }
//    Bitmap bitmap;
//    byte[] data;

//    private void openImagePicker() {
//        TedBottomPicker.OnImageSelectedListener onImageSelectedListener = new TedBottomPicker.OnImageSelectedListener() {
//            @Override
//            public void onImageSelected(Uri uri) {
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
//                    data = getBitmapAsByteArray(bitmap);
//                    img_item.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getContext())
//                .setOnImageSelectedListener(onImageSelectedListener).create();
//        tedBottomPicker.show(getParentFragmentManager());
//    }
//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//        return outputStream.toByteArray();
//    }

}