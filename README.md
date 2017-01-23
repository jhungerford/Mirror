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
 * Create/configure your development db in `config/dev.secret.exs`
 * Create and migrate the database with `mix ecto.create && mix ecto.migrate`
 * Start the Phoenix endpoint with `mix phoenix.server`

Visit http://localhost:4000 in your browser.

## Raspberry Pi
 * Elixir: http://elixir-lang.org/install.html#raspberry-pi
 * Phoenix deployment guide: http://www.phoenixframework.org/docs/deployment

### Phoenix links
 * Official website: http://www.phoenixframework.org/
 * Guides: http://phoenixframework.org/docs/overview
 * Docs: http://hexdocs.pm/phoenix
 * Mailing list: http://groups.google.com/group/phoenix-talk
 * Source: https://github.com/phoenixframework/phoenix
