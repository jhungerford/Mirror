Mirror
======

Webpage for an old monitor behind a mirror I connected to a Raspberry Pi.  Displays useful stuff.

React + Phoenix stack inspired by this series of blog posts: http://codeloveandboards.com/blog/2016/01/04/trello-tribute-with-phoenix-and-react-pt-1/.

## Similar Projects
A couple other people have put together 'magic mirrors'.  Examples:
* https://www.raspberrypi.org/blog/magic-mirror/
* https://github.com/MichMich/MagicMirror
* http://imgur.com/gallery/q8Vv4

## Environment Variables
Mirror uses the following environment variables for configuration:
| Name | Description |
|------|-------------|
| MIRROR_FORECASTIO_API_KEY | Api key from https://darksky.net/dev - used for weather |
| MIRROR_LATITUDE | Your latitude - used for weather |
| MIRROR_LONGITUDE | Your longitude - used for weather |

## Development
This project uses the dark sky forecast api for weather.  Go to https://darksky.net/dev, and create an account.

To start the app:
* Install elixir dependencies with `mix deps.get`
* Install js dependencies with `npm install`
* Start the Phoenix endpoint with `mix phoenix.server`

Visit http://localhost:4000 in your browser.
