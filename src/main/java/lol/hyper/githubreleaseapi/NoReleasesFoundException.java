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

public class NoReleasesFoundException extends RuntimeException {

    /**
     * Thrown when a GitHub repository doesn't have any releases.
     *
     * @param errorMessage The error message included with the exception.
     */
    public NoReleasesFoundException(String errorMessage) {
        super(errorMessage);
    }
}
