package de.feli490.feliutils;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;

import de.iani.playerUUIDCache.CachedPlayerProfile;

public class HeadUtils {
    private HeadUtils() {
        throw new Error("nope.");
    }

    public static String getSkinURLFromPlayerProfile(PlayerProfile profile) {
        return profile == null ? null : getSkinURLFromProfilePropertySet(profile.getProperties());
    }

    public static String getSkinURLFromCachedPlayerProfile(CachedPlayerProfile profile) {
        return profile == null ? null : getSkinURLFromProfilePropertySet(profile.getProperties());
    }

    public static String getSkinURLFromProfilePropertySet(Set<ProfileProperty> properties) {

        String profilePropertyValue = ermittleTexturePropertyValue(properties);
        return profilePropertyValue == null ? null : getSkinURLFromTexturesProperty(profilePropertyValue);
    }

    public static String ermittleTexturePropertyValue(Set<ProfileProperty> properties) {
        for (ProfileProperty prop : properties) {
            if (prop.getName().equals("textures")) {
                return prop.getValue();
            }
        }
        return null;
    }

    public static String getSkinURLFromTexturesProperty(String value) {
        try {
            String decodedValue = new String(java.util.Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
            if (decodedValue.startsWith("{textures:{SKIN:{url:")) { // fix old broken json
                decodedValue = decodedValue.replace("{textures:{SKIN:{url:", "{\"textures\":{\"SKIN\":{\"url\":");
            }
            JSONObject json = (JSONObject) new JSONParser().parse(decodedValue);
            JSONObject jsonTextures = (JSONObject) json.get("textures");
            JSONObject jsonSkin = (JSONObject) jsonTextures.get("SKIN");
            return (String) jsonSkin.get("url");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static String getTexturesProperyFromSkinURL(String url) {
        JSONObject json = new JSONObject();
        JSONObject jsonTextues = new JSONObject();
        JSONObject jsonSkin = new JSONObject();
        json.put("textures", jsonTextues);
        jsonTextues.put("SKIN", jsonSkin);
        jsonSkin.put("url", url);
        byte[] serialized;
        serialized = json.toString().getBytes(StandardCharsets.UTF_8);
        return java.util.Base64.getEncoder().encodeToString(serialized);
    }
}
