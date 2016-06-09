#Changelog
---
####5.7.3
- Merged user/channel message private commands
- Devoice Geffy if he joins #bl4ckscor3
- Fixed private commands not working
- Fixed LinkManager sometimes executing ShowTweet code if "twitter" was present in the link itself
- Fixed YouTube stats' date and views for (probably) all languages using latin letters
- Internal changes

####5.7.2
- The user now gets notified if the sent Tweet doesn't exist
- -forge's version subcommand now shows the build date
- Fixed YouTube Info not showing if the uploader disabled dis-/likes
- Fixed -caps sending its output to the user as a pm instead to the channel
- Fixed private stop command not working
- Internal changes

**5.7.1.1**
- Added Java version to -info

####5.7.1
- -longurl now uses the unshorten.it API
- Changed -longurl to -unshorten
- Internal changes

##5.7
- Reminders now get saved over downtimes
- Fixed YT Video info not showing "0" when the video hasn't been viewed yet
- de_DE fixes
- Internal changes

####5.6.3
- Misc. fixes

####5.6.2
- Embedded Tweets (Tweets within Tweets) now get sent to the channel, too
- Added space infront of some links in Tweets for readability
- Added alias for "dlsrc" in -forge ("dlmdk")
- Fixed -forge not giving a reply when there was nothing found
- Fixed -ranks not working

####5.6.1
- Added message if CSGOSquad is in maintenance mode
- Added automatic conversion to decimal if WolframAlpha's result is a fraction
- Fixed l in un-/leet being displayed as | instead of 1

##5.6
- Added private command to identify with nickserv
- Removed debug output
- Typo fixes
- Fix for imgur albums
- Internal changes

##5.5
- Added private command to edit the config
- Added message to -changenick if the nick is already in use
- Added message to -changenick if the nick is the same the bot is named at the moment
- Removed private messaging other OPs
- Fixed typo in -ranks
- More RAM fix attempts
- Internal changes

####5.4.5
- Added auto-kick in \#bl4ckscor3 when a non i.imgur.com link is used
- Colors can now be used within language files
- Internal changes

####5.4.4
- Added new alias to -select (-choose)
- Fixed shrugs not always triggering
- Fixed -evaluate only using first argument for the request
- Internal changes

####5.4.3
- Re-enabled isVerified checks because of fix in PBX 2.1
- Fixed typos and formatting in -w messages
- Added configuration value for SpellingCorrection
- RAM improvements (?)
- Internal changes

####5.4.2
- Added build date to -info
- Fixed shrugs emote not working

**5.4.1.1**		
- Updated to PircBotX 2.1

####5.4.1
- -ranks message formatting fix
- Added mp/h to wind speed in -w
- Fixed Kelvin having a long number
- Possible memory improvements
- Internal changes

##5.4
- Added -ranks (shows CS:GO rank distribution)
- Added restriction for users to check/stop reminders in the wrong channel
- Added restriction for users to check/stop reminders that do not own the reminder

####5.3.1
- Added ability to stop reminders
- Added ability to gather info of reminders
- Added ability to list active reminders
- Fixed -remind not working when only seconds have been given
- Fixed -mcstatus explanation not working
- Internal changes

##5.3
- Added -remind
- Fixed help not showing completely for commands without aliases

####5.2.1
- Fixed -w not working

##5.2
- Added -unleet
- Removed unneccessary/unused commands:
	- -cfwidget
	- -letter
	- -number
	- -reverse
	- -scramble
	- -vowels

####5.1.1
- Removed new config values added in last version
- Fixed Tweets not showing
- Removed colors from logging

##5.1
- Changed behaviour of -scramble (now scrambles letters in a word)
- New config values
- Fixed "O" and "o" not converting to "0" in -leet
- Internal changes

#5.0
- Added a configuration file to configure commands and all that jizz
- Removed unwanted debug output
- Small log message changes

####4.9.1
- Fixed some aliases not working
- Fixed uptime not displaying correctly
- Internal changes

##4.9
- Added command aliases. For a full list, please visit [ALIASES.md](https://github.com/bl4ckscor3/bl4ckb0t/blob/master/ALIASES.md).
- Added one more message to versatile translation
- Removed -draw (unneccessary)
- Removed unused class
- Fixed some -eval (former -calc) issues
- Fixed Action Event not ignoring users

##4.8
- Added -info
- Added specific reply to a specific action
- Removed -version
- Fixed -help not getting triggered all the time
- Fixed GitHub language showing null, if non existed
- Fixed logging message showing up when it shouldn't

####4.7.2
- Fixed SpellingCorrection localization being incorrect

####4.7.1
- Commas now get replaced with dots, to prevent the API from thinking multiple equations have been entered
- Added feedback message when the API cannot find a result
- Fixed division not working

##4.7
- Reimplemented -calc
- Fixed link at the end of the weather message not working
- Improvements to localization to be more versatile
- Made more messages localizable
- GitHub commit stats for long normal commit links
- Internal changes

####4.6.2
- Stacktraces now get logged
- More logging while starting the bot

####4.6.1
- Error logging improvements
- Fixed title of colored/formatted links not being shown

##4.6
- Added change stats of a commit if a git.io link is posted
- Fixed -draw not showing available drawings
- The whole message now gets replaced if the second part in s/x/y is null

####4.5.2
- Fixed -w
- Fixed Tweets with hashtags and mentions not showing correctly
- Fixed pictures within Tweets not showing links in message
- Internal changes

####4.5.1
- Mavenized
- Fixed language not being set when joining channels
- Fixed channel states not working in some situations
- Fixed only showing the first link when there are multiple Tweets or multiple GitHub links in one message

##4.5
- Added stop command to private message
- Fixed a bug where sending messages too fast would result in an incorrect language being used
- Fixed private message commands getting sent to valid users
- Internal changes

**4.4.3.1**
- Reduced message delay even more

####4.4.3
- Reduced message delay
- -kick no longer requires a reason
- Changed how messaging commands to the bot via PM works
- Fixed ignored users not getting ignored in some situations

####4.4.2
- Removed spammy property of -enable and -disable
- Changed some messages

####4.4.1
- Added arguments (up and low) to -caps
- Bot reconnects after PTO now

##4.4
- Added -caps
- Added -cfwidget
- Removed ability for other valid users to see all private messages sent to bl4ckb0t
- Changed the help menu up a little
- Internal changes

##4.3
- Reimplemented -forge
- Fixed YouTubeStats again
- Fixed space appearing instead of nothing when using s/word/
- Internal changes

####4.2.4
- Fixed YouTubeStats
- Fixed showing Tweets for the most part, links etc. still don't work
- Removed unused commands

####4.2.3
- Changed black colored messages to use the default color so users with a dark client can read the messages

####4.2.2
- Fixed log file not being created

####4.2.1
- Changed logging file location to jarLocation/logs
- WIP bot activated with program argument

##4.2
- Bot marks himself as away if disabled globally
- Logging to file

##4.1
- Added -ping
- Fixed channel join logging showing twice
- Fixed kick logging message displaying incorrect name
- Memory improvement using ArrayMap

####4.0.2
- Added another password channel
- Changed up some messages of commands
- #akino_germany language gets set automatically
- Removed Cracked Kicker (was moved to [Geffy](http://github.com/Geforce132/Geffy))
- Internal changes

####4.0.1
- Removed annoying debug output in GitHub feature
- Internal changes

#4.0
- New Logging system
- SpellingCorrection fixes & improvements
- Fixed dis-/likes not showing
- Fixed "Verified Account" showing

##3.11
- Added -trello
- Added showing GitHub repository stats if a link is posted, similar to YouTube stats
- Added permission system
- Help menu only shows commands the user has permission to
- Tweets now get shown in the chat instead of the page title
- Removed sentence from source command, only sends the link now
- Tweaks

##3.10
- Added user ignore list
- Change in German localization
- Reverted Spelling Correction to previous italics replacement
- Fixed -select, -listchans, -join, -leave and many others
- Internal changes

##3.9
- Added automatic kicking of users using a cracked Minecraft client in #GeforceMods

####3.8.2
- Removed spying feature due to incomplete search for users in the channel. This fixes the bot not working

####3.8.1
- Fixed bot not connecting to the correct channels

##3.8
- Added -update
- Disabled bot in channel #whatever by default
- Bot sends all PMs to all valid users (users who are able to control the bot)
- Bot connects through SSL now
- Fixed -changenick

##3.7
- Added -reverse to reverse your sentences
- Disabled -forge
- SpellingCorrection can now replace character sequences with single spaces (s/sequence/ ")
- Only actual corrected parts become italics (e.g. if "tset" would be corrected to "test" instead of everything being italics, only "es" is italics)
- -w now doesn't expect only one argument, you can freely use spaces now

####3.6.1
- WIP versions can't be shown via -changelog anymore
- Fixed -select

##3.6
- Added -changelog
- Added language support to -xcolor
- Internal changes

####3.5.4
- Added argument which allows specific users to show the secret channels the bot is in
- Got rid of StringBuilders where not needed
- No more Java 7 in help menu
- Fixed enable and disable notification messages not getting sent to other channels
- Fixed bot not recognizing that he already joined/left a channel when being asked to join/leave

####3.5.3
- Fixed italics getting negated when corrected message gets corrected again
- Compacted help menu
- Language adjustments
- Internal changes

####3.5.2
- Added setup of allowed users
- Fixed bot trying to join channels multiple times when -stop yes is used

####3.5.1
- Made Spelling Correction changes appear in italics
- Internal changes (Lambdas)

##3.5
- Disabled -calc
- Changed storage of messages in SpellingCorrection to be more flexible
- The bot can now be disabled/enabled per channel

##3.4
- Added -version
- Fixed -decide not working as intended
- Internal changes to improve performance

####3.3.1
- Fixed -isitdown help
- Fixed bl4ckb0t sending a message if the corrected message is the same as before
- Fixes regarding localization
- Internal changes

##3.3
- Added -isitdown

##3.2
- bl4ckb0t will mirror messages into #whatever if bl4ckscor3 is not in the channel
- Links in LinkTitle are shortened to 20 symbols to prevent channel cluttering
- Specific links can get blacklisted to not be shown the title of
- Version is now shown within the code
- Removed unneccessary debug code
- Removed #whatever as a password channel
- Fixed and added -mcstatus (again)
- Fixed duplicate sending of help messages
- Internal changes

##3.1
- Added #whatever as a password channel
- The language of the current channel can now be retrieved via -language
- Updated -forge for 1.8 full release builds
- Number of secret channels joined gets shown when using -listchans
- Moved passwords to seperate file
- Fixed localization for LinkTitle
- Fixed a typo
- Fixed regex replacement in spelling correction
- Internal changes

####3.0.1
- Added german translation
- Changed wording where neccessary
- Moved handling of commands within private messages to own package
- Fixed localization not working in multiple channels
- Fixed some messages not showing due to wrong localization 
- Fixed wrong localization path in -leave
- Fixed localization for spelling 
- Fixed -stop localization
- Internal changes

#3.0
- Added language support
- Updated -source link
- Removed -deads

##2.22
- Added -join and -leave to private messages in order to be able to join channels with passwords

####2.21.1
- Fixed spelling correction not working in multiple channels
- Fixed secret channels showning in -listchans
- Internal changes

##2.21
- Added spelling correction (regex replacement)
- Fixed "welcome back" message not showing if only "re" was posted

##2.20
- bl4ckb0t now detects multiple YouTube links in one message and sends their 
- bl4ckb0t can now join and leave his default channels
- Updated -forge for 1.8 beta versions
- Revamped scrambling algorithm
- Revamped way of telling the user that he has done something incorrectly
- Performance adjustments
- Removed -bukkit and -cbukkit due to DMCA takedown
- Fixed youtu.be links not working
- Fixed publishing date not
- Fixed "welcome back" message
- Internal changes

##2.19
- Completely new way of handling help
- Updated -forge for full release versions
- Adapted -ets2mp to new website
- Moved users who are able to fully control bl4ckb0t to Dropbox
- Fixed versions with spaces not working using -ets2mp
- Internal changes

##2.18
- Added -scramble
- Updated help menu
- Updated -forge for beta versions
- Removed self kick
- Removed unneccessary code to increase performance
- Fixed "disabled" message not showing
- Fixed NullPointerException when less than 20 channels are 
- Fixed YouTubeStats for the new YouTube
- Fixed video id not being able to be found if it wasn't at the beginning of the link
- Fixed -leet only outputting 
- Another attempt on automatic rejoining after a ping timeout

##2.17
- Added -ets2mp
- bl4ckb0t no longer shows .0 if the solution is a whole number in -calc
- Changed sorry message
- YouTubeStats message now uses default IRC client color
- Fixed YouTubeStats not showing correctly if the element could not be found
- Fixed YouTubeStats not working when the video posted has "301+ views"
- Fixed typo in -decide

##2.16
- Rewrote YouTubeStats to use Selenium
- Fixed message formatting
- Internal changes

####2.15.3
- Tweaked help menu
- More efficient user validation
- Link now gets shown when the title of it is sent
- Removed suicide message and related
- Removed automatic reconnect on ping timeout
- Fixed channel file link not working
- Fixed link not working when no protocol was given
- Internal changes

####2.15.2
- Added -mcstatus to the normal bot
- Updated help menu
- Moved allowed users and users not to kick to Dropbox
- Removed unneccessary drawings
- Fixed registering added in 2.15.1
- Attempt to fix -deads
- Internal changes

####2.15.1
- Fixed -mcstatus not working as intended
- The testbot now registers commands by himself
- Internal changes

##2.15
- Added -mcstatus

####2.14.1
- Disabled -deads
- Changed waiting time of -deads
- Fixed # check

##2.14
- Added -deads
- Updated help menu
- Added check for the channel's # in -join and -leave
- Internal changes

##2.13
- Rewrote -w to be more efficient

####2.12.1
- No message will get sent if there is no title

##2.12
- bl4ckb0t will tell the title of the website if a link has been posted

##2.11
- bl4ckb0t now automatically joins multiple channels when he starts up
- bl4ckb0t will tell other channels that he has been enabled/disabled
- Changed server again
- Internal changes

##2.10
- Removed -u because the new forum software allows for much easier linking to users
- Removed unneccessary debug output
- Fixed -w not working correctly

##2.9
- Added -listchans
- Updated help menu

####2.8.2
- Tweaked -forge a bit more

####2.8.1
- Added Minecraft version to message in -forge

##2.8
- Rewrote -forge to be more efficient

##2.7
- Added -join
- Added -leave
- Updated help menu
- Removed -girlballs
- Removed random "lol" response
- Subaraki is no longer awesome (Ok, he still is!)
- Internal changes

####2.6.1
- Added detection of youtu.be links
- Fixed links with an h not working with LongURL
- Fixed YouTubeStats for english YouTube (because tested in German YouTube
- Fixed "re" message firing if "remember" or similar is posted in the chat

##2.6
- If a YouTube video link is posted, information about it is sent

##2.5
- Added -longurl
- Fixed typos

####2.4.2
- Added how-to to -u when something has been done incorrectly
- Fixed bumped up limit

####2.4.1
- Updated help menu
- Bumped up limit in -letter and -number to 50
- Fixed -w not showing any results when the website did not show a wind direction
- Fixed resource leaks
- Internal changes

##2.4
- Added -w to retrieve the current weather of a city
- Changed server again
- Tweaks in -kick
- Internal changes

####2.3.1
- Added simple nick reset
- Removed some unneccessary debug output

##2.3
- bl4ckb0t can now send personal messages to others
- Updated help menu (Thanks [akino_germany](https://github.com/akino-germany/)!)

####2.2
- Added -select
- Removed -filter
- Removed sending image if suicide is contained in a message

####2.1.1
- Fixed -forge

##2.1
- Added -xcolor
- Added recommended build check to -forge
- Added factorial to -calc
- Added revival after someone "* kills himself"
- Fixed suicide thing for actions

####2.0.4
- Added sending a picture if someone "* kills himself"

####2.0.3
- Added modulo calculations to -calc
- Removed update reminder
- Attempt to correctly restart bot on ping timeout
- Finally fixed enable check when disabled

####2.0.2
- Started -filter
- Fixed check if the bot is enabled when iterating through commands

####2.0.1
- Added ?enabled command to check if the bot is enabled
- Internal changes

#2.0
- New way of handling commands (Thanks [Lord_Ralex](https://github.com/AE97/)!)

##1.15
- Added -cbukkit (CraftBukkit)
- Improved -bukkit algorithm

##1.14
- Added -bukkit (similar to -forge)
- Fixed -leet conversion

####1.13.3
- Bot now reconnects on a ping timeout

####1.13.2
- Tweaks to -calc
- Added 2²-type calculations
- Removed * kicks <name> message

####1.13.1
- Added reminder for <s>TehKitti</s>Vauff to update bl4ckb0t

##1.13
- Added -calc
- Removed redundant message

####1.12.3
- Updated help menu
- Removed jokes
- Removed -say
- Removed -time
- Internal changes

####1.12.2
- -decide now requires a questionmark (?) to work
- Fixed bot not being able to be enabled again

####1.12.1
- Changed source message again
- Subaraki is awesome!

##1.12
- Added -leet to 1337ify your messages
- Added -vowels to get rid of all the vowels in your messages
- Removed eastereggs
- Removed funmode
- Removed debug mode
- Internal changes

####1.11.1
- If something was done incorrectly with -u, all the available arguments get sent
- Fancied up help menu

##1.11
- Added -decide which answers to a question with yes or no
- bl4ckb0t will masturbate with a 0.1% chance after a message

####1.10.1
- Updated -dl link
- bl4ckb0t will say "Yeah, lol" with a 5% chance after a sent message if it starts with "lol"
- Removed greeting on nick change

##1.10
- Added -dl command for <s>TehKitti</s>Vauff to get the update link
- Added quit message
- Fixed some features triggering if bot was disabled

####1.9.6
- Added how-to to -latestforge if something was done incorrectly
- Fixed -kick sometimes not sending the correct message

####1.9.5
- Changed server from aperture.esper.net to chaos.esper.net
- Changed way of getting bl4ckb0t's source
- Internal changes

####1.9.4
- Added two drawings
- Internal changes

####1.9.3
- Added ten drawings
- Added option to activate funmode for a specific amount of time
- Internal changes

####1.9.2
- Fixed bl4ckb0t repeating -fun when in funmode

####1.9.1
- Fixed funmode not working as intended

##1.9
- Added funmode (repeates every message sent)

####1.8.3
- Optimized -draw

####1.8.2
- Optimized -latestforge

####1.8.1
- Updated help menu
- Fixed source command

##1.8
- Added -latestforge

####1.7.1
- Added a drawing
- Internal changes

##1.7
- Added -draw

##1.6
- Added -girlballs

####1.5.1
- Updated help menu

##1.5
- Added -yt to retrieve a YouTube channel link
- Added -tw to retrieve a Twitter profile link
- Added -tv to retrieve a Twitch channel link

####1.4.1
- bl4ckb0t welcomes bl4ckscor3

##1.4
- bl4ckb0t can now talk
- bl4ckb0t now sends the pms he received to bl4ckscor3
- Only bl4ckscor3 is being loved
- Internal changes

####1.3.5
- Seperated users who are not allowed to be kicked from users who are allowed to kick others

####1.3.4
- Removed custom leave message argument from -stop

####1.3.3
- -changenick is now only accessible if -debug is enabled

####1.3.2
- Added argument to -stop to allow for a custom leave message

####1.3.1
- Fixed -kick being able to be used by users with the correct name, but who are not logged in

##1.3 
- Added -kick command to kick a user from the channel

##1.2
- Added -changenick command to change the bot's nickname
- Internal changes

##1.1
- Changed server from irc.esper.net to aperture.esper.net
- Added spam protection when using -say *say with <s>TehKitti</s>Vauff's bot in the same channel

#1.0
- Initial release
