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
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GitHubReleaseAPI {

    private JSONArray array;
    private List<GitHubRelease> releases;
    private final String repoURL;
    private final String repositoryName;
    private final String organizationName;

    /**
     * Create an API object. This object is bound to 1 repository.
     *
     * @param repoName The repository name.
     * @param orgName  The name of the user OR organization the repository is on.
     * @throws IOException Thrown if there is an error making the request.
     */
    public GitHubReleaseAPI(@NotNull String repoName, @NotNull String orgName) throws IOException {
        this.array = readGitHubAPI(repoName, orgName);
        this.releases = getReleases();
        this.repoURL = "https://github.com/" + orgName + "/" + repoName;
        this.organizationName = orgName;
        this.repositoryName = repoName;
    }

    /**
     * Get all the releases the GitHub project.
     *
     * @return A list of all the releases.
     */
    public @Nullable List<GitHubRelease> getAllReleases() {
        if (releases.isEmpty()) {
            throw new NoReleasesFoundException(repoURL);
        }
        return releases;
    }

    /**
     * Get how many versions behind a release is.
     *
     * @param release The release you want to check.
     * @return The number of builds behind from (release) to (latest)
     */
    public int getBuildsBehind(GitHubRelease release) {
        if (releases.isEmpty()) {
            throw new NoReleasesFoundException(repoURL);
        }
        return releases.indexOf(release);
    }

    /**
     * Get the latest release.
     *
     * @return Latest release.
     */
    public @NotNull GitHubRelease getLatestVersion() {
        if (releases.isEmpty()) {
            throw new NoReleasesFoundException(repoURL);
        }
        return releases.get(0);
    }

    /**
     * Get a release by a given tag.
     *
     * @param tag The tag to search for.
     * @return The release from given tag.
     */
    public @Nullable GitHubRelease getReleaseByTag(@NotNull String tag) {
        if (releases.isEmpty()) {
            throw new NoReleasesFoundException(repoURL);
        }
        for (GitHubRelease release : releases) {
            if (release.getTagVersion().equalsIgnoreCase(tag)) {
                return release;
            }
        }
        return null;
    }

    /**
     * Reads the GitHub API of a project.
     *
     * @return A JSONArray with all the info.
     */
    private @NotNull JSONArray readGitHubAPI(String repoName, String orgName) throws IOException {
        String remoteRaw;
        URL url = new URL("https://api.github.com/repos/" + orgName + "/" + repoName + "/releases");
        URLConnection conn = url.openConnection();
        conn.setRequestProperty(
                "User-Agent",
                "GitHubReleaseAPI https://github.com/hyperdefined/GitHubReleaseAPI");
        conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
        conn.connect();

        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        remoteRaw = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        reader.close();
        return new JSONArray(remoteRaw);
    }

    /**
     * Grab all the releases of a GitHub project.
     *
     * @return List with all the versions.
     */
    private @NotNull List<GitHubRelease> getReleases() {
        List<GitHubRelease> releases = new ArrayList<>();
        JSONArray remoteVersions = array;
        if (remoteVersions.isEmpty()) {
            throw new NoReleasesFoundException(repoURL);
        }

        for (int i = 0; i < remoteVersions.length(); i++) {
            GitHubRelease temp = new GitHubRelease(remoteVersions.getJSONObject(i));
            releases.add(temp);
        }
        return releases;
    }

    /**
     * Get the repository's URL.
     * @return GitHub URL to the repository.
     */
    public String getRepoURL() {
        return repoURL;
    }

    /**
     * Refresh the releases for this repository.
     */
    public void refreshReleases() throws IOException {
        this.array = readGitHubAPI(repositoryName, organizationName);
        this.releases = getReleases();
    }
}
