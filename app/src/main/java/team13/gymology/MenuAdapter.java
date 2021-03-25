package team13.gymology;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//public class MenuAdapter extends FragmentStateAdapter {
//
//    public MenuAdapter(@NonNull FragmentActivity fragmentActivity) { super(fragmentActivity); }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        Fragment fragment = null;
//        if (position == 0) {
//            // Profile Screen
//            fragment = new Profile();
//            Log.d("Fragments", "Creating Profile Fragment.");
//        } else if (position == 1) {
//            // Workouts DB Screen
//            fragment = new WorkoutDatabase();
//            Log.d("Fragments", "Creating WorkoutDB Fragment.");
//        } else if (position == 2) {
//            // Home Screen
//            Toast.makeText(this, "Welcome Home", Toast.LENGTH_SHORT).show();
//        } else if (position == 3) {
//            // Statistics
//            fragment = new Statistics();
//            Log.d("Fragments", "Creating Statistics Fragment.");
//        } else if (position == 4) {
//                // Calendar Screen
//            fragment = new ExerciseDetails();
//            Log.d("Fragments", "Creating ExerciseDetails Fragment.");
//        }
//        return fragment;
//    }
//    @Override
//    public int getItemCount() {
//        /* The number of fragments managed by the adapter */
//        return 5;
//    }
//
//}
