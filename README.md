## Usage
Add this to your `pom.xml`:
```xml
<dependency>
    <groupId>lol.hyper</groupId>
    <artifactId>github-release-api</artifactId>
    <version>1.0.1</version>
</dependency>
```
First, access your repo's API with `GitHubReleaseAPI`.
* `repo-name`: The repository's name.
* `org OR username`: Your GitHub's username or the organization's name. This is where the repository is. If it's on an organization, then you would put the organization's name. If it's on your own account, put your username.
```java
GitHubReleaseAPI api = new GitHubReleaseAPI("repo-name", "org OR username");

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

// Is the release a draft?
release.isDraft();

// Is the release a pre-release?
release.isPreRelease();

//Gets the "regular" link to the GitHub release page.
release.getRegularLink();
```
