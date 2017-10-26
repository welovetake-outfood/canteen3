package com.example1.mycanteen;

import android.app.Activity;
//import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CanteenlistFragment extends ListFragment {
  private Callbacks mCallbacks;
  public interface Callbacks{
    public void onItemSelected(Integer id);
  }
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.fragment_canteenlist);
    setListAdapter(new ArrayAdapter<Schoolcanteen.Canteen> (getActivity(),
        android.R.layout.simple_list_item_activated_1,Schoolcanteen.ITEMS));
  }

  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof Callbacks)) {
      throw new IllegalStateException("error in canteenlistfragment");
    }
    mCallbacks=(Callbacks)activity;
  }
  
  public void onDetach() {
    super.onDetach();
    mCallbacks=null;
  }
  
  public void onListItemClick(ListView listView,View view,int position,long id) {
    super.onListItemClick(listView, view, position, id);
    mCallbacks.onItemSelected(Schoolcanteen.ITEMS.get(position).id);
  }
  
  public void setActivateOnItemClick(boolean activateonItemClick) {
    getListView().setChoiceMode(activateonItemClick ? ListView.CHOICE_MODE_SINGLE :ListView.CHOICE_MODE_NONE);
  }
}
