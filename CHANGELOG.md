###############################
#          Changelog          #
###############################
3.6.3
- Fixes regarding Localization
- Revamped way of telling the user that he has done something incorrectly internally

3.6.2
- bl4ckb0t doesn't send a message if the corrected message is the same as before

3.6.1
- Fixed -isitdown help

3.6
- Added -isitdown

3.5.8
- Internal changes

3.5.7
- Removed some debug output
- Fixed duplicate sending of help messages

3.5.6
- Fixed and added -mcstatus (again)

3.5.5
- Specific links can get blacklisted to not be shown the title of
- Removed #whatever as a password channel

3.5.4
- Links in LinkTitle are shortened to 20 symbols to prevent channel cluttering

3.5.3
- Version is now shown within the code

3.5.2
- Changed onOrivateMessage message

3.5.1
- Removed unneccessary debug code
- Internal changes

3.5
- bl4ckb0t is a spy

3.4.4
- Added #whatever as a password channel

3.4.3
- Fixed regex replacement in spelling correction

3.4.2
- Updated -forge for 1.8 full release builds

3.4.1
- Number of secret channels joined gets shown when using -listchans

3.4
- Moved passwords to seperate file

3.3.3
- Fixed a typo
- Internal changes

3.3.2
- The language of the current channel can now be retrieved via -language
- Fixed localization for LinkTitle
- Internal changes

3.3.1
- Fixed -stop localization

3.3
- Moved handling of commands within private messages to own package

3.2.5
- Internal changes

3.2.4
- Fixed localization for spelling correction

3.2.3
- Fixed wrong localization path in -leave

3.2.2
- Fixed a typo

3.2.1
- Completed german translation
- Changed wording where neccessary
- Fixed some messages not showing due to wrong localization paths

3.2
- Fixed localization not working in multiple channels
- Started german translation

3.1.3
- Removed faulty code

3.1.2
- Updated localization for -scramble

3.1.1
- Updated -source link
- Added localization to -source (forgot)

3.1
- Removed -deads

3.0
- Added language support

2.24.6
- Added -join and -leave to private messages in order to be able to join channels with passwords

2.24.5
- Internal changes

2.24.4
- Secret channels don't get shown in -listchans

2.24.3
- Fixed spelling correction not working in multiple channels

2.24.2
- Fixed "welcome back" message not showing if only "re" was posted

2.24.1
- Other users are now able to correct others

2.24
- Added spelling correction (regex replacement)

2.23.1
- Performance adjustments
- Fixed "welcome back" message

2.23
- Revamped way of telling the user that he has done something incorrectly

2.22.4
- Maximum algorythm length set to 20 (REF: God's number)

2.22.3
- Revamped scrambling algorythm

2.22.2
- Fixed 2.22.1

2.22.1
- Updated -forge for 1.8 beta versions

2.22
- Removed -bukkit and -cbukkit
- Internal changes

2.21.2
- Fixed publishing date not showing

2.21.1
- Fixed youtu.be links not working

2.21
- bl4ckb0t can now join and leave his default channel

2.20
- bl4ckb0t now detects multiple YouTube links in one message and sends their stats

2.19.10
- Adapted -ets2mp to new website

2.19.9
- Added more notes to -changenick help

2.19.8
- Internal changes

2.19.7
- Fixed 2.19.5

2.19.6
- Internal changes

2.19.5
- Moved users who are able to fully control bl4ckb0t to Dropbox

2.19.4
- Fixed versions with spaces not working using -ets2mp

2.19.3
- Updated -forge for full release versions

2.19.2
- Fixed 2.19.1

2.19.1
- Prevent message flooding when sending help

2.19
- Completely new way of handling help

2.18.12
- Updated -forge for beta versions

2.18.11
- Another attempt on automatic rejoining after a ping timeout

2.18.10
- Fixed -leet only outputting lowercased

2.18.9
- Removed unneccessary code to increase performance

2.18.8
- Removed unneccessary code to increase performance

2.18.7
- Internal changes

2.18.6
- Internal changes

2.18.5
-Fixed video id not being able to be found if it wasn't at the beginning of the link

2.18.4
- Adapted YouTubeStats to new layout from YouTube

2.18.3
- Fixed "disabled" message not showing
- Fixed NullPointerException when less than 20 channels are joined

2.18.2
- Removed self kick

2.18.1
- Updated help menu

2.18
- Added -scramble

2.17.8
- Fixed typo in -decide

2.17.6
- Changed sorry message

2.17.5
- Removed base black when sending YouTubeStats

2.17.4
- Fixed 2.17.3

2.17.3
- bl4ckb0t doesn't show .0 if the solution is a whole number in -calc

2.17.2.
- Fixed YoutTubeStats not working when the video posted has "301+ views"

2.17.1
- Fixed YouTubeStats not showing correctly if the element could not be found

2.17
- Added -ets2mp

2.16.3
- Fixed 2.16.2

2.16.2
- Internal changes

2.16.1
- Fixed message formatting

2.16
- Rewrote YouTubeStats to use Selenium

2.15.14
- Link now gets shown when the title is sent
- Fixed link not working when no protocol was given

2.15.13
- Removed automatic reconnect on ping timeout

2.15.12
- More efficient user validation

2.15.11
- Removed suicide message and related
- Tweaked help menu
- Internal changes

2.15.10
- Fixed channel file link not working

2.15.9
- Fixed channel file link not working

2.15.8
- Added -mcstatus to the normal bot
- Attempt to fix -deads

2.15.7
- Removed unneccessary drawings

2.15.6
- Updated help menu
- Internal changes

2.15.5
- Moved allowed users and users not to kick to Dropbox

2.15.4
- Fixed registeristering added in 2.15.3

2.15.3
- The testbot now registers commands by itself

2.15.2
- Internal changes

2.15.1
- Fixed -mcstatus not working as intended

2.15
- Added -mcstatus

2.14.9
- Changed waiting time of -deads

2.14.8
- Fixed version 2.14.6

2.14.7
- Disabled -deads

2.14.6
- Added check for the channel's # in -join and -leave

2.14.5
- Internal changes

2.14.4
- Reverted to version 2.14.1

2.14.3
- Internal changes

2.14.2
- Added own channel for the test bot to join

2.14.1
- Updated help menu

2.14
- Added -deads

2.13
- Rewrote -w to be more efficient

2.12.1
- No message will get sent if there is no title

2.12
- bl4ckb0t will tell the title of the website if a link has been posted

2.11.4
- Internal changes

2.11.3
- bl4ckb0t will tell other channels that he has been enabled/disabled

2.11.2
- Internal changes

2.11.1
- Internal changes

2.11
- bl4ckb0t now automatically joins multiple channels when he starts up
- Changed server again

2.10.2
- Removed unneccessary debug output

2.10.1
- Fixed -w not working correctly

2.10
- Removed -u because the new forum software allows for much easier linking to users

2.9.1
- Updated help menu

2.9
- Added -listchans

2.8.2
- Tweaked -forge a bit more

2.8.1
- Added Minecraft version to message in -forge

2.8
- Rewrote -forge to be more efficient

2.7.2
- Removed -girlballs
- Removed random "lol" response
- Subaraki is no longer awesome

2.7.1
- Updated help menu

2.7
- Added -join
- Added -leave
- Internal changes

2.6.4
- Fixed "re" message firing if remember or similar is posted in the chat

2.6.3
- Fixed YouTubeStats for English YouTube (because tested in German YouTube)

2.6.2
- Added detection of youtu.be links

2.6.1
- Fixed links with an h not working with LinkTitle

2.6
- If a YouTube link is posted, bl4ckb0t sends some information about the video

2.5.1
- Fixed typos

2.5
- Added -longurl

2.4.6
- Added how-to to -u when something has been done incorrectly

2.4.5
- Fixed bumped up limit

2.4.4
- Fixed -w not showing any results when the website did not show a winddirection

2.4.3
- Updated help menu

2.4.2
- Bumped up limit in -letter and -number to 50
- Internal changes

2.4.1
- Fixed resource leaks

2.4
- Added -w
- Changed server again

2.3.2
- Internal changes

2.3.1
- Added simple nick reset
- Removed some unneccessary debug output

2.3
- bl4ckb0t can now send personal messages to others

2.2.1
- Updated help menu

2.2
- Added -select
- Removed -filter

2.1.1
- Fixed -forge

2.1
- Added recommended build check to -forge
- Added -xcolor
- Added factorial to -calc
- Added revival after someone "* kills himself"

2.0.5
- Internal changes

2.0.4
- Added sending a picture if someone "* kills himself"

2.0.3
- Added % (modulo) to -calc
- Attempt to correctly restart bot on ping timeout
- Removed update reminder
- Finally fixed enable check when disabled

2.0.2
- Added -filter
- Fixed check if the bot is enabled when iterating through commands

2.0.1
- Added ?enabled command to check if the bot is enabled
- Internal changes

2.0
- New way of handling commands

1.14.2
- Bot now reconnects on a ping timeout

1.14.1
- Tweaks to -calc
- Added 2² calculations
- Removed * kicks <name> message

1.14
- Added reminder for TehKitti to update bl4ckb0t

1.13
- Added -calc

1.12.4
- Removed jokes
- Updated help menu

1.12.3
- Removed -say
- Removed -time
- Internal changes

1.12.2
- -decide now requires a questionmark (?) to work
- Fixed bot not being able to be enabled again

1.12.1
- Changed source message again
- Subaraki is awesome!

1.12
- Added -leet to 1337ify your messages
- Added -vowels to get rid of all the vowels in your messages
- Removed eastereggs
- Removed funmode
- Removed debug mode

1.11.2
- Internal changes

1.11.1
- If something was done incorrectly with -u, all the available arguments get sent
- Fancied up help menu

1.11
- Added -decide which answers to a question with yes or no
- bl4ckb0t will masturbate with a 0.1% chance after a message

1.10.10
- bl4ckb0t will say "Yeah, lol" with a 5% chance after a sent message if it starts with "lol"

1.10.9
- Internal changes

1.10.8
- Added how-to to -latestforge if something was done incorrectly
- Fixed -kick sometimes not sending the correct message

1.10.7
- Changed server from aperture.esper.net to chaos.esper.net
- Changed way of getting bl4ckb0t's source
- Internal changes
1.10.6
- Internal changes

1.10.5
- Added two drawings

1.10.4
- Internal changes

1.10.3
- Added ten drawings
- Added option to activate funmode for a specific amount of time
- Internal changes

1.10.2
- Fixed bl4ckb0t repeating -fun when in funmode

1.10.1
- Fixed funmode not working as intended

1.10
- Added funmode (repeates every message sent)

1.9.3
- Optimized -draw

1.9.2
- Optimized -latestforge

1.9.1
- Updated help menu
- Fixed source command

1.9
- Added -latestforge

1.8.1
- Added a drawing
- Internal changes

1.8
- Added -draw

1.7
- Added -girlballs

1.6.1
- Updated help menu

1.6
- Added -yt to retrieve a YouTube channel link
- Added -tw to retrieve a Twitter profile link
- Added -tv to retrieve a Twitch channel link

1.5.1
- bl4ckb0t welcomes bl4ckscor3

1.5
- bl4ckb0t can now talk
- bl4ckb0t now sends the pms he received to bl4ckscor3
- Only bl4ckscor3 is being loved
- Internal changes

1.4.4
- Seperated users who are not allowed to be kicked from users who are allowed to kick others

1.4.3
- Removed custom leave message argument from -stop

1.4.2
- -changenick is now only accessible if -debug is enabled

1.4.1
- Added argument to -stop to allow for a custom leave message

1.4
- Fixed -kick being able to be used by users with the correct name, but who are not logged in

1.3 
- Added -kick command to kick a user from the channel

1.2
- Added -changenick command to change the bot's nickname
- Internal changes

1.1
- Changed server from irc.esper.net to aperture.esper.net
- Added spam protection when using -say *say with TehKitti's bot in the same channel

1.0
- Initial release