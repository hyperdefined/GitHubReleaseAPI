## Usage
Add this to your `pom.xml`:
```xml
<dependency>
    <groupId>lol.hyper</groupId>
    <artifactId>github-release-api</artifactId>
    <version>1.0.0</version>
</dependency>
```
First, access your repo's API with `GitHubReleaseAPI`.
* `repo-name`: The repository's name.
* `org/username`: Your GitHub's username or the organization's name.
```java
GitHubReleaseAPI api = new GitHubReleaseAPI("repo-name", "org/username");

// Example
GitHubReleaseAPI api = new GitHubReleaseAPI("MyProject", "hyperdefined");
```
Now that we have access, we can use some of the functions. Each release is stored as a `GitHubRelease` object.
```java
// Gets all of the releases on the repo.
api.getAllReleases();

// Gets how many releases behind a certain release is.
// Pass it a GitHubRelease object.
api.getBuildsBehind(release);

// Gets the latest release.
api.getLatestVersion();

// Gets a release by the tag.
// Pass it a string of said tag.
api.getReleaseByTag(tag);
```
The `GitHubRelease` object stores a bunch of information about a release.
```java
// Gets the tag.
release.getTagVersion();

// Gets the GitHub release notes.
release.getReleaseNotes();

// Gets when the release was published.
release.getPublishedDate();

// Gets when the released was first created.
release.getCreatedDate();

// Gets the attached files on the release.
// These are direct links to attached files.
release.getReleaseAssets();
