Mirror
======

Webpage for an old monitor behind a mirror I connected to a Raspberry Pi.  Displays useful stuff.

I ride my bike to work most days, so 'useful' stuff helps me decide whether to take the bus.

## Similar Projects
A couple other people have put together 'magic mirrors'.  Examples:
* https://www.raspberrypi.org/blog/magic-mirror/
* https://github.com/MichMich/MagicMirror
* http://imgur.com/gallery/q8Vv4

## Things the mirror could display
* [ ] Time / Date
* [ ] Calendar - work + personal
* [ ] Weather - forecast for the next week, high/low today
* [ ] Radar map
* [ ] Bus schedule

## Environment Variables
Mirror uses several environment variables for configuration.

| Name                      | Description                                                    |
| ------------------------- | -------------------------------------------------------------- |
| MIRROR_FORECASTIO_API_KEY | Api key from https://developer.forecast.io/ - used for weather |
| MIRROR_LATITUDE           | Your latitude - used for weather                               |
| MIRROR_LONGITUDE          | Your longitude - used for weather                              |

## Developing
This project uses the dark sky forecast api for weather.  Go to https://developer.forecast.io/, and create an account.

To run in IntelliJ, set the environment variables listed above and run the MirrorMain class with program arguments ```server mirror.yml```
