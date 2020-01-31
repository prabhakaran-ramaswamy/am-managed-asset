package org.sample.capstone.helper;

import java.util.List;

import org.sample.capstone.HibernateProxyTypeAdapter;
import org.sample.capstone.entity.ManagedAsset;
import org.sample.capstone.model.ManagedAssetModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ManagedAssetUtil {

	public static ManagedAssetModel copyManagedAssetToManagedAssetModel(ManagedAsset managedAsset) {
	    GsonBuilder b = new GsonBuilder();
	    b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	    Gson gson = b.create();
	    String text = gson.toJson(managedAsset);
	    ManagedAssetModel newObject = gson.fromJson(text, ManagedAssetModel.class);
	    return newObject;
	}
	
	public static ManagedAsset copyManagedAssetModelToManagedAsset(ManagedAssetModel managedAssetModel) {
	    GsonBuilder b = new GsonBuilder();
	    b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	    Gson gson = b.create();
	    String text = gson.toJson(managedAssetModel);
	    ManagedAsset newObject = gson.fromJson(text, ManagedAsset.class);
	    return newObject;
	}
	
	public static List<ManagedAssetModel> copyManagedAssetsToManagedAssetModels(List<ManagedAsset> list) {
	    GsonBuilder b = new GsonBuilder();
	    b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	    Gson gson = b.create();
	    String text = gson.toJson(list);
	    TypeToken<List<ManagedAssetModel>> token = new TypeToken<List<ManagedAssetModel>>() {};
	    List<ManagedAssetModel> newObject = gson.fromJson(text, token.getType());
	    return newObject;
	}
	
	public static List<ManagedAsset> copyManagedAssetModelsToManagedAssets(List<ManagedAssetModel> managedAssetModels) {
	    GsonBuilder b = new GsonBuilder();
	    b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	    Gson gson = b.create();
	    String text = gson.toJson(managedAssetModels);
	    TypeToken<List<ManagedAsset>> token = new TypeToken<List<ManagedAsset>>() {};
	    List<ManagedAsset> newObject = gson.fromJson(text, token.getType());
	    return newObject;
	}
}
