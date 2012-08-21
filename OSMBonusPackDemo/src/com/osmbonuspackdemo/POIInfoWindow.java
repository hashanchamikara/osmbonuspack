package com.osmbonuspackdemo;

import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.bonuspack.overlays.DefaultInfoWindow;
import org.osmdroid.bonuspack.overlays.ExtendedOverlayItem;
import org.osmdroid.views.MapView;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

/**
 * A customized InfoWindow handling POIs. 
 * We inherit from DefaultInfoWindow as it already provides most of what we want. 
 * And we just add support for a "more info" button. 
 * 
 * @author M.Kergall
 */
public class POIInfoWindow extends DefaultInfoWindow {
	
	private POI mSelectedPOI;
	
	public POIInfoWindow(MapView mapView) {
		super(R.layout.bonuspack_bubble, mapView);
		
		Button btn = (Button)(mView.findViewById(R.id.bubble_moreinfo));
			//yes, bonuspack_bubble layouts already contain a "more info" button. 
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (mSelectedPOI.mUrl != null){
					Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mSelectedPOI.mUrl));
					view.getContext().startActivity(myIntent);
				}
			}
		});
	}

	@Override public void onOpen(ExtendedOverlayItem item){
		super.onOpen(item);
		mSelectedPOI = (POI)item.getRelatedObject();
		if (mSelectedPOI.mUrl != null)
			mView.findViewById(R.id.bubble_moreinfo).setVisibility(View.VISIBLE);
		else
			mView.findViewById(R.id.bubble_moreinfo).setVisibility(View.GONE);
	}
}