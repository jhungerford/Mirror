defmodule PhoenixMirror.PageController do
  use PhoenixMirror.Web, :controller

  def index(conn, _params) do
    render conn, "index.html"
  end
end
