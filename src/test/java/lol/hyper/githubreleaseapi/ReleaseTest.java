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

import org.junit.Test;

import java.io.IOException;

public class ReleaseTest {

    @Test
    public void outputRepoReleases() {
        GitHubReleaseAPI api = new GitHubReleaseAPI("ToolStats", "hyperdefined");

        System.out.println("Latest version: " + api.getLatestVersion());

        for (GitHubRelease release : api.getAllReleases()) {
            System.out.println(release);
        }
    }

    @Test
    public void invalidRelease() {
        GitHubReleaseAPI api = new GitHubReleaseAPI("ToolStats", "hyperdefined");
        try {
            GitHubRelease invalidTag = api.getReleaseByTag("invalid-tag");
        } catch (ReleaseNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void outputReleaseDetails() {
        GitHubReleaseAPI api = new GitHubReleaseAPI("ToolStats", "hyperdefined");
        GitHubRelease latest = api.getLatestVersion();
        System.out.println(latest.getTagVersion());
        System.out.println(latest.getReleaseNotes());
        System.out.println(latest.getPublishedDate());
        System.out.println(latest.getCreatedDate());
        System.out.println(latest.getReleaseAssets());
        System.out.println(latest.isDraft());
        System.out.println(latest.isPreRelease());
        System.out.println(latest.getReleaseURL());
    }
}
