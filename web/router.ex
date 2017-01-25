defmodule PhoenixMirror.Router do
  use PhoenixMirror.Web, :router

  pipeline :browser do
    plug :accepts, ["html"]
    plug :fetch_session
    plug :fetch_flash
    plug :protect_from_forgery
    plug :put_secure_browser_headers
  end

  pipeline :api do
    plug :accepts, ["json"]
    # plug Guardian.Plug.VerifyHeader
    # plug Guardian.Plug.LoadResource
  end

  scope "/", PhoenixMirror do
    pipe_through :browser

    get "/*path", PageController, :index
  end

  # scope "/api", PhoenixMirror do
  #   pipe_through :api
  # end
end
