package lol.hyper.githubreleaseapi;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GitHubRelease {

    private final JSONObject object;

    protected GitHubRelease(JSONObject object) {
        this.object = object;
    }

    /**
     * Gets the tag version of this release.
     *
     * @return The tag version.
     */
    public @NotNull String getTagVersion() {
        return object.getString("tag_name");
    }

    /**
     * Gets the release notes for this tag version.
     *
     * @return A string with the release notes.
     */
    public @NotNull String getReleaseNotes() {
        return object.getString("body");
    }

    /**
     * Gets the time the release was published on GitHub.
     *
     * @return A string with the date.
     */
    public @NotNull String getPublishedDate() {
        return object.getString("published_at");
    }

    /**
     * Gets the time the release was first created on GitHub.
     *
     * @return A string with the date.
     */
    public @NotNull String getCreatedDate() {
        return object.getString("created_at");
    }

    /**
     * Get a list of the uploaded files on this tag version.
     *
     * @return A list of direct links to uploaded files.
     */
    public @NotNull List<String> getReleaseAssets() {
        List<String> urls = new ArrayList<>();
        JSONArray releases = object.getJSONArray("assets");
        for (int i = 0; i < releases.length(); i++) {
            JSONObject temp = releases.getJSONObject(i);
            urls.add(temp.getString("browser_download_url"));
        }
        return urls;
    }
}
