/*
 * This file is part of GitHubReleaseAPI.
 *
 * GitHubReleaseAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GitHubReleaseAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GitHubReleaseAPI.  If not, see <https://www.gnu.org/licenses/>.
 */

package lol.hyper.githubreleaseapi;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GitHubRelease {

    private final JSONObject object;

    /**
     * Creates a release object of a certain tag on GitHub.
     * @param object The JSON object of the release.
     */
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

    /**
     * Is the release a draft?
     *
     * @return True/False if the release is a draft.
     */
    public boolean isDraft() {
        return object.getBoolean("draft");
    }

    /**
     * Is the release a pre-release?
     *
     * @return True/False if the release is a pre-release.
     */
    public boolean isPreRelease() {
        return object.getBoolean("prerelease");
    }

    /**
     * Gets the HTML link of the release. This is the "regular" release page.
     *
     * @return The URL of release.
     */
    public String getRegularLink() {
        return object.getString("html_url");
    }
}
